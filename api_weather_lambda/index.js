exports.handler = async (event) => {
    if (event.httpMethod === 'GET') {
        return determineDress(event);
    }
};

const determineDress = event => {

}

