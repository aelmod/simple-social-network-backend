angular
    .module('ssnApp')
    .controller('ConversationController', ConversationController);

function ConversationController($scope, $http) {
    $http
        .get('/api/conversations')
        .then((res) => {
            $scope.conversations = res.data;
        })
}