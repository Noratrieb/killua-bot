#!/usr/bin/env bash

docker run -e "BOT_TOKEN=TOKEN" -e "KILLUA_JSON_PATH=/app/trivia_questions.json" \
  -v "$(pwd)/trivia_questions.json:/app/trivia_questions.json" \
  --name killua-bot -d killua-bot:1.0