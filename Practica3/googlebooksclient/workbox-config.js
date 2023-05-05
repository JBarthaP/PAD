module.exports = {
	globDirectory: 'src/',
	globPatterns: [
		'**/*.{css,jsx,js,svg}'
	],
	swDest: 'src/sw.js',
	ignoreURLParametersMatching: [
		/^utm_/,
		/^fbclid$/
	]
};