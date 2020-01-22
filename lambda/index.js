exports.handler = async (event) => {
    if (event.httpMethod === 'GET') {
        return getItem(event);
    }

    if (event.httpMethod === 'POST') {
        return createCart(event);
    }
};


const getItem = event => {
    let item = {
        description: 'A red t-shirt',
        color: 'red',
        size: 'medium',
        price: '$1.22'
    };
    return {
        statusCode: 200,
        body: JSON.stringify(item)
    };
};

const createCart = event => {
    let body = JSON.parse(event.body);
    console.log('this was the cart that was passed up', body)
    return {
        statusCode: 200,
        body: JSON.stringify({
            message: 'The cart was created'
        })
    }
};