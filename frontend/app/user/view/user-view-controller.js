angular
    .module('ssnApp.user')
    .controller('UserViewController', UserViewController);


function UserViewController($scope, $http, $stateParams) {
    $http
        .get('/api/users/' + $stateParams.userId, {headers: {"X-Token": 'eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJTaW1wbGVTb2NpYWxOZXR3b3JrMiIsInVzZXJJZCI6Mn0.LXjsEIVtA8ClS2OmMN8emaHQHUwwRTdtC84NoHRllIA5yci4w5m1Wo2rqEKHSYhbhZfgpurzzIZziMrkVfOjgg'}})
        .then((res) => {
            $scope.user = res.data;
        });

}