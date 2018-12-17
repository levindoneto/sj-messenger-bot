#!/usr/bin/env bash
curl -X POST -H "Content-Type: application/json" -d '{
"setting_type":"call_to_actions",
"thread_state":"new_thread",
"call_to_actions":[
 {
    "payload":"GET_STARTED"
 }
]
}' "https://graph.facebook.com/v2.6/me/thread_settings?access_token=EAADz7gTuL0cBAC2ZAh01yJ2V71uI4ki4uxWetLYXjZC9ZALZCNDR71ZCSZBylAo1sKkNzHHOdlPFXNhZBiBdJy8lMLZBYlxTrdYPC01nCBotx5BOd6TWt25LgEgwDGUgIoWq6mmxdWrGg950BXabcxF5z0Oxbtvf5bsrOhCZA6gp5Tz2L7X4pG732"