call npx @redocly/cli@latest bundle
call npx @redocly/cli@latest join -o bundled/mastro.yaml --prefix-tags-with-filename

call npx @redocly/cli@latest bundle apis/mastro-ai.yaml -o bundled/mastro-ai.yaml 
call npx @redocly/cli@latest join bundled/mastro.yaml bundled/mastro-ai.yaml  -o bundled/monolith.yaml --prefix-tags-with-filename
