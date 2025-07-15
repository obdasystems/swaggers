# OpenAPI specifications
[![pages-build-deployment](https://github.com/obdasystems/swaggers/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/obdasystems/swaggers/actions/workflows/pages/pages-build-deployment)

This repository contains the definitions of the available REST APIs served by Mastro.


## APIs

- [mastro (whole specs)](https://obdasystems.github.io/swaggers/ui/?api=mastro)

Generated with th following command:

```
npx @redocly/cli@latest join -o bundled/mastro.yaml --prefix-tags-with-filename
```

If you are interested in a subset of API we distribute also this smaller bundles:

- [authentication](https://obdasystems.github.io/swaggers/ui/?api=authentication)
- [ontologies](https://obdasystems.github.io/swaggers/ui/?api=ontologies)
- [mappings](https://obdasystems.github.io/swaggers/ui/?api=mappings)
- [datasources](https://obdasystems.github.io/swaggers/ui/?api=datasources)
- [endpoints](https://obdasystems.github.io/swaggers/ui/?api=endpoints)
- [query-catalog](https://obdasystems.github.io/swaggers/ui/?api=query-catalog)
- [sparqling](https://obdasystems.github.io/swaggers/ui/?api=sparqling)
- [triplify](https://obdasystems.github.io/swaggers/ui/?api=triplify)
- [virtual-entities](https://obdasystems.github.io/swaggers/ui/?api=virtual-entities)
- [vkg](https://obdasystems.github.io/swaggers/ui/?api=vkg)
- [knowledge-graphs](https://obdasystems.github.io/swaggers/ui/?api=knowledge-graphs)
- [ontology-designer](https://obdasystems.github.io/swaggers/ui/?api=ontology-designer)
- [ontology-catalog](https://obdasystems.github.io/swaggers/ui/?api=ontology-catalog)
- [authorization-view-profiles](https://obdasystems.github.io/swaggers/ui/?api=authorization-view-profiles)
- [dashboards](https://obdasystems.github.io/swaggers/ui/?api=dashboards)
- [data-quality](https://obdasystems.github.io/swaggers/ui/?api=data-quality)
- [misc](https://obdasystems.github.io/swaggers/ui/?api=misc)
- [task-scheduler](https://obdasystems.github.io/swaggers/ui/?api=task-scheduler)
- [users-roles](https://obdasystems.github.io/swaggers/ui/?api=users-roles)

Generated with th following command:

```
npx @redocly/cli@latest bundle
```

## External APIs

- [ai-assistant](https://obdasystems.github.io/swaggers/ui/?api=ai-assistant)
