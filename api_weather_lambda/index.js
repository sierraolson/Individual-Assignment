exports.handler = async (event) => {
    if (event.httpMethod === 'GET') {
        return determineDress(event);
    }
};

const request = require('request')

const getWeather = function(lat, lon) {
    let api_key = 'cf60835a202d9fe0cde90a932a4ff7ed';

    let url = `http://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&units=imperial&appid=${api_key}`
    request(url, function (err, response, body) {
        if(err){
          console.log('error:', error);
        } else {
            let weather = JSON.parse(body)
            console.log(weather)
            let message = `It's ${weather.main.temp} degrees in ${weather.name}!`;
            let determineDress;
            let temp_val = parseFloat(weather.main.temp);
            if (temp_val < 32) {
                determineDress = 'Below freezing right now! Bundle up!'
            } else if (temp_val >= 32 && temp_val < 60) {
                determineDress = 'Kinda chilly, bring a jacket'
            } else {
                determineDress = 'Feel free to ditch the jacket!'
            }
            console.log(message);
            console.log(determineDress)
        }
    });
}


const determineDress = event => {
    let lat = '33.7490'
    let lon = '-84.3880';
    getWeather(lat, lon)
}

determineDress()