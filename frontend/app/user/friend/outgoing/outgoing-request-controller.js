angular
    .module('ssnApp.user')
    .controller('FriendOutgoingRequestController', FriendOutgoingRequestController);

function FriendOutgoingRequestController($scope, $http) {
    $http
        .get('/api/users/friends/outgoingRequests')
        .then((res) => {
            $scope.outgoingRequests = res.data;
        })
}