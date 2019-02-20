const authHeader = () => {
    let user = JSON.parse(localStorage.getItem('user'));

    if (user) {
        return { 'Authorization': 'Beaurer ' + user };
    } else {
        return {};
    }
}

export default authHeader;