npx @redocly/cli@latest bundle
npx @redocly/cli@latest join -o bundled/mastro.yaml --prefix-tags-with-filename

npx @redocly/cli@latest bundle apis/ai-assistant.yaml -o bundled/ai-assistant.yaml 