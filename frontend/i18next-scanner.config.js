module.exports = {
  input: [
    'src/**/*.{js,jsx}',
    '!src/**/*.test.{js,jsx}',
    '!src/i18n/**',
    '!**/node_modules/**',
  ],
  output: './',
  options: {
    debug: true,
    func: {
      list: ['i18next.t', 'i18n.t', 't'],
      extensions: ['.js', '.jsx'],
    },
    trans: {
      component: 'Trans',
      i18nKey: 'art',
      defaultsKey: 'defaults',
      extensions: ['.js', '.jsx'],
      fallbackKey: function (ns, value) {
        return value
      },
      acorn: {
        ecmaVersion: 10,
        sourceType: 'module',
      },
    },
    lngs: ['en', 'hu', 'sr'],
    defaultLng: 'en',
    defaultValue: function (lng, ns, key) {
      return key
    },
    resource: {
      loadPath: 'src/i18n/{{lng}}.json',
      savePath: 'src/i18n/{{lng}}.json',
      jsonIndent: 2,
      lineEnding: '\n',
    },
    nsSeparator: false,
    keySeparator: '.',
    interpolation: {
      prefix: '{{',
      suffix: '}}',
    },
    sort: true,
    removeUnusedKeys: false,
  },
}
