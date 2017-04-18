angular
    .module('ssnApp.user')
    .controller('FriendOutgoingRequestController', FriendOutgoingRequestController);

function FriendOutgoingRequestController($scope, $http) {
    $http
        .get('/api/users/friends/outgoingRequests', {headers: {"X-Token": localStorage.token}})
        .then((res) => {
            $scope.outgoingRequests = res.data;
        })
}