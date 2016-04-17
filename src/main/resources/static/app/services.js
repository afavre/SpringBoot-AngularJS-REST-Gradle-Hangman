(function (angular) {
	var ItemFactory = function ($resource) {
		return $resource('/items/:id', {
			id: '@id'
		}, {
			update: {
				method: "PUT"
			}
			, remove: {
				method: "DELETE"
			}
		});
	};

	var GameFactory = function ($resource) {
		var path = "/hangman/game";
		return $resource(path + '/:letter', {
			letter: '@letter'
		}, {
			query: {
				method: 'GET'
				, isArray: false
			}
			, queryNew: {
				url: path + '/new'
				, method: 'GET'
				, isArray: false
			}
			, getSolution: {
				url: path + '/solution'
				, method: 'GET'
				, isArray: false
			}
			, getAll: {
				url: path + '/all'
				, method: 'GET'
				, isArray: true
			}
		});
	};

	GameFactory.$inject = ['$resource'];
	angular.module("myApp.services").factory("Game", GameFactory);
}(angular));