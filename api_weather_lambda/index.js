const request = require('request')

const requestHandler = (url) => new Promise((resolve, reject) => {
    request(url, (error, response, body) => {
    if (error) {
        console.error(error);
        reject(error);
    } else {
        console.log(response, body);
        resolve({ response, body });
    }
  });
});

exports.handler = async (event) => {
    if (event.httpMethod === 'GET') {
        let api_key = 'cf60835a202d9fe0cde90a932a4ff7ed';
        let lat = '33.7490'
        let lon = '-84.3880';
        let url = `http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=imperial&appid=${api_key}`
        const { response, body } = await requestHandler(url);
        let weather = JSON.parse(body)
        let message = `It's ${weather.main.temp} degrees in ${weather.name}!`;
        let determineDress;
        let temp_val = parseFloat(weather.main.temp);
        if (temp_val < 32) {
            determineDress = 'Below freezing right now! Bundle up!'
        } else if (temp_val <= 32 && temp_val > 60) {
            determineDress = 'Kinda chilly, bring a jacket'
        } else {
            determineDress = 'Feel free to ditch the jacket!'
        }
        let ret = {
            statusCode: 200,
            body: JSON.stringify({
                message: message,
                determineDress: determineDress
            })
        };
        console.log(ret)
        return ret;
    }
};