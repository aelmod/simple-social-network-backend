angular
    .module('ssnApp.user')
    .controller('FriendIncomingRequestController', FriendIncomingRequestController);

function FriendIncomingRequestController($scope, $http) {
    $http
        .get('/api/users/friends/incomingRequests', {headers: {"X-Token": localStorage.token}})
        .then((res) => {
            $scope.incomingRequests = res.data;
        })
}