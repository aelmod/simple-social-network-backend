angular
    .module('ssnApp')
    .controller('MicroblogController', MicroblogController);

function MicroblogController($http) {
    this.$onInit = () => {
        $http
            .get('/api/microblog/' + this.microblogId)
            .then((res) => {
                this.microblog = res.data;
            });
    };

    this.addComment = (microblogId, commentaryForm) => {
        $http
            .post('/api/microblog/' + microblogId + '/commentaries', commentaryForm)
            .then((res) => {
                this.microblog.commentaries.push(res.data);
                commentaryForm.text = '';
            });
    };
}