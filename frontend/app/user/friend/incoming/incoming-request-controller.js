angular
    .module('ssnApp.user')
    .controller('FriendIncomingRequestController', FriendIncomingRequestController);

function FriendIncomingRequestController($scope, $http) {
    $http
        .get('/api/users/friends/incomingRequests')
        .then((res) => {
            $scope.incomingRequests = res.data;
        })
}