angular
    .module('ssnApp')
    .controller('HomeController', HomeController);


function HomeController($http, $location) {
    if (localStorage.token === undefined) $location.path('/login');
    else {
        $http
            .get('/api/users/currentUser')
            .then((res) => {
                $location.path('/user/' + res.data.id);
            });
    }
}