#!/usr/bin/env bash
echo '___________________ Sao Joao ____________________|'
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
          "title":"Atendimento",
          "type":"postback",
          "payload":"CUSTOMER_SERVICE_PAYLOAD"
        },
        {
          "title":"Serviços",
          "type":"postback",
          "payload":"SERVICES_PAYLOAD"
        },
        {
          "title":"Ver mais",
          "type":"nested",
          "call_to_actions":[
            {
              "title":"Produtos",
              "type":"postback",
              "payload":"PRODUCTS_PAYLOAD"
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
                  "title":"Oportunidades",
                  "type":"postback",
                  "payload":"OPORTUNITIES_PAYLOAD"
                },
                {
                  "title":"Outros Serviços",
                  "type":"postback",
                  "payload":"OTHER_SERVICES_PAYLOAD"
                }
              ]
            }
          ]
        }
      ]
    }
  ]
}' "https://graph.facebook.com/v2.6/me/messenger_profile?access_token=EAAaliTZBYzq4BACbxUoFxxmEyv2lsU1AG6OmLyzGbQSqxoWmIiptA28ZBhRjBhZCL6W7GE4O28TfsTIunSGmZBZClIKfOXyTRgOZBIZBApO2mOfAZAsT01ihjdbZBJZCFzpC6ssIX4gVnFZChJWGxbPofeyOepnGHHZAMwgWVW5cmZBWwoqiz74IptDLq"
echo ''