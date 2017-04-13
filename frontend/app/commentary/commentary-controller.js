angular
    .module('ssnApp')
    .controller('CommentaryController', CommentaryController);

function CommentaryController($scope, $http, $state) {
    $scope.addComment = (microblogId, commentaryForm) => {
        $http
            .post('/api/microblog/' + microblogId + '/commentaries/add', commentaryForm, {headers: {"X-Token": localStorage.token}})
            .then(() => {
                    $state.reload();
                }
            )
    };
}