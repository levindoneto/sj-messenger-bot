#!/usr/bin/env bash
echo '___________________ WeEnvoyer ___________________|'
echo 'Setting up three buttons in the persistent menu  |'
echo 'Atendimento                                      |'
echo 'Serviços                                         |'
echo 'Ver mais >                                       |'
echo '_________________________________________________|'

curl -X POST -H "Content-Type: application/json" -d '{
  "persistent_menu":[
    {
      "locale":"default",
      "composer_input_disabled": false,
      "call_to_actions":[
        {
          "title":"Nossos Serviços",
          "type":"postback",
          "payload":"SERVICES_PAYLOAD"
        },
        {
          "title":"Sintomas",
          "type":"postback",
          "payload":"SYMPTOMS_PAYLOAD"
        },
        {
          "title":"Ver mais",
          "type":"nested",
          "call_to_actions":[
            {
              "title":"Atendimento",
              "type":"postback",
              "payload":"CUSTOMER_SERVICE_PAYLOAD"
            },
            {
              "title":"Localidade",
              "type":"postback",
              "payload":"LOCALIZATION_PAYLOAD"
            },
            {
              "title":"Ver mais",
              "type":"nested",
              "call_to_actions":[
                {
                  "title":"Cadastrar",
                  "type":"postback",
                  "payload":"REGISTER_PAYLOAD"
                },
                {
                  "title":"Exames",
                  "type":"postback",
                  "payload":"EXAMS_PAYLOAD"
                },
                {
                  "title":"Mensagem direta",
                  "type":"postback",
                  "payload":"DIRECT_MESSAGE"
                }
              ]
            }
          ]
        }
      ]
    }
  ]
}' "https://graph.facebook.com/v2.6/me/messenger_profile?access_token=EAADz7gTuL0cBAC2ZAh01yJ2V71uI4ki4uxWetLYXjZC9ZALZCNDR71ZCSZBylAo1sKkNzHHOdlPFXNhZBiBdJy8lMLZBYlxTrdYPC01nCBotx5BOd6TWt25LgEgwDGUgIoWq6mmxdWrGg950BXabcxF5z0Oxbtvf5bsrOhCZA6gp5Tz2L7X4pG732"
echo ''