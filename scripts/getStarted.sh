#!/usr/bin/env bash
curl -X POST -H "Content-Type: application/json" -d '{
"setting_type":"call_to_actions",
"thread_state":"new_thread",
"call_to_actions":[
 {
    "payload":"GET_STARTED"
 }
]
}' "https://graph.facebook.com/v2.6/me/thread_settings?access_token=EAAaliTZBYzq4BACbxUoFxxmEyv2lsU1AG6OmLyzGbQSqxoWmIiptA28ZBhRjBhZCL6W7GE4O28TfsTIunSGmZBZClIKfOXyTRgOZBIZBApO2mOfAZAsT01ihjdbZBJZCFzpC6ssIX4gVnFZChJWGxbPofeyOepnGHHZAMwgWVW5cmZBWwoqiz74IptDLq"