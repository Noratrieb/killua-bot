#!/usr/bin/env bash

docker run -e "BOT_TOKEN=LOL" -e "KILLUA_JSON_PATH=/app/trivia_questions.json" \
  -v "$(pwd)/trivia_questions.json:/app/trivia_questions.json" \
  --name killua-bot killua-bot:1.0