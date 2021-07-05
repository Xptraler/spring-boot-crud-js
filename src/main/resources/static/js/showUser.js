fetch('/api/users' )
    .then((response) => {
        return response.json();
    })
    .then((data) => {
        console.log(data);
        console.log()
    });
