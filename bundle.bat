call npx @redocly/cli@latest bundle
call npx @redocly/cli@latest join -o bundled/mastro.yaml --prefix-tags-with-filename

call npx @redocly/cli@latest bundle apis/ai-assistant.yaml -o bundled/ai-assistant.yaml 
call npx @redocly/cli@latest join bundled/mastro.yaml bundled/ai-assistant.yaml  -o bundled/monolith.yaml --prefix-tags-with-filename
