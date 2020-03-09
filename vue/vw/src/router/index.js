import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

const Home = r => require.ensure([], () => r(require('@/pages/Home')), 'Home');
const film = r => require.ensure([], () => r(require('@/pages/film')), 'film');
const theater = r => require.ensure([], () => r(require('@/pages/theater')), 'theater');

const routes = [
	{
			path: '/',
			component: Home,
			name: '',
	},
	{
			path: '/film',
			component: film,
			name: '',
	},
	{
			path: '/theater',
			component: theater,
			name: '',
	}
]


export default new Router({

	routes,

	strict: process.env.NODE_ENV !== 'production',

})

