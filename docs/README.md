Website
=======

This website is built using [Docusaurus](https://docusaurus.io/), a modern static website generator.

Installation
------------

```console
yarn
```

Local Development
-----------------

```console
yarn start
```

This command starts a local development server and opens up a browser window. Most changes are reflected live without
having to restart the server.

Build
-----

```console
yarn build
```

This command generates static content into the `build` directory and can be served using any static contents hosting
service.

Deployment
----------

We use
[GitHub Action for automatic site deployment](https://github.com/paion-data/athena/blob/master/.github/workflows/ci-cd.yml).
