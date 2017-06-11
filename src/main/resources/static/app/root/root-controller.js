angular
    .module('ssnApp')
    .controller('RootController', RootController);


function RootController($location) {
    if (localStorage.token === undefined) $location.path('/login');
    else $location.path('/home');
}