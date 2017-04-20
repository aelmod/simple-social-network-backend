angular
    .module('ssnApp')
    .component('microblog', {
        controller: 'MicroblogController',
        templateUrl: 'app/microblog/microblog-view.html',
        bindings: {
            microblogId: '='
        }
    });