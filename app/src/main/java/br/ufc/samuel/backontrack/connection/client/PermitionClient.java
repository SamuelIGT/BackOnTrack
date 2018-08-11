package br.ufc.samuel.backontrack.connection.client;

import android.util.Log;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

public class PermitionClient {
    private String url;
    private RestTemplate restTemplate;

    public PermitionClient(String baseUrl) {
        this.url = baseUrl + "/permitions";
        this.restTemplate = new RestTemplate();
    }

    public String[] requestPermition(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(" ", headers);

        String response[] = {"", ""};

        try{
            response[1] = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            Log.d("PERMITION CLIENT: ", ""+response[1]);
            //            response = restTemplate.getForObject(url, entity, String.class);
            response[0] = ""+HttpURLConnection.HTTP_OK;
        }catch (Exception e){
            response[0] = e.getMessage();
            response[1] = "Token pode ter expirado";
            e.printStackTrace();
        }

        response[1] = responseMock();//TODO:DELETAR

        return response;
    }



















    //TODO: DELETAR
    private String responseMock(){
        return "[\n" +
                "    {\n" +
                "        \"id\": 17,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"2018422231030\",\n" +
                "            \"password\": \"$2a$10$MfngT28Polzp6s.iCHWX0./bhgVEgItkO./EW.iy0saNIVE9YuYhS\",\n" +
                "            \"registration\": \"2018422231030\",\n" +
                "            \"name\": \"JOAO MOREIRA DA SILVA\",\n" +
                "            \"phone\": \"8592827382\",\n" +
                "            \"parent\": \"FRANCISCA MOREIRA DA SILVA\",\n" +
                "            \"phoneParent\": \"8881772736\",\n" +
                "            \"progress\": 1,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422231030\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 28,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 61,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 33,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 29,\n" +
                "                \"title\": \"CORTANDO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 30,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"videoY.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 32,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 31,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:40:41.142+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    }\n" +
                "]";
    }

    private String responseMock2(){

        return "[\n" +
                "    {\n" +
                "        \"id\": 17,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"2018422231030\",\n" +
                "            \"password\": \"$2a$10$MfngT28Polzp6s.iCHWX0./bhgVEgItkO./EW.iy0saNIVE9YuYhS\",\n" +
                "            \"registration\": \"2018422231030\",\n" +
                "            \"name\": \"JOAO MOREIRA DA SILVA\",\n" +
                "            \"phone\": \"8592827382\",\n" +
                "            \"parent\": \"FRANCISCA MOREIRA DA SILVA\",\n" +
                "            \"phoneParent\": \"8881772736\",\n" +
                "            \"progress\": 1,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422231030\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 28,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 61,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 33,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 29,\n" +
                "                \"title\": \"CORTANDO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 30,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"videoY.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 32,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 31,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:40:41.142+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 59,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 50,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"GARÇOM AVANÇADO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 46,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 48,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 47,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:44:09.897+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 60,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 40,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 37,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 36,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.278+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 62,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 33,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 29,\n" +
                "                \"title\": \"CORTANDO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 30,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"videoY.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 32,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 31,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:40:41.142+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 58,\n" +
                "            \"email\": \"2018422234652\",\n" +
                "            \"password\": \"$2a$10$1k2pTfvij9jUbWfkijhUR.hARvFE1qrG5O0OVZBY3XIusIphN5Y96\",\n" +
                "            \"registration\": \"2018422234652\",\n" +
                "            \"name\": \"FRANCISCO MARIO FERREIRA\",\n" +
                "            \"phone\": \"85996522180\",\n" +
                "            \"parent\": \"CARLOS WAGNER FERREIRA\",\n" +
                "            \"phoneParent\": \"85993331268\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234652\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 64,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 58,\n" +
                "            \"email\": \"2018422234652\",\n" +
                "            \"password\": \"$2a$10$1k2pTfvij9jUbWfkijhUR.hARvFE1qrG5O0OVZBY3XIusIphN5Y96\",\n" +
                "            \"registration\": \"2018422234652\",\n" +
                "            \"name\": \"FRANCISCO MARIO FERREIRA\",\n" +
                "            \"phone\": \"85996522180\",\n" +
                "            \"parent\": \"CARLOS WAGNER FERREIRA\",\n" +
                "            \"phoneParent\": \"85993331268\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234652\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 65,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 40,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 37,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 36,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.278+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 58,\n" +
                "            \"email\": \"2018422234652\",\n" +
                "            \"password\": \"$2a$10$1k2pTfvij9jUbWfkijhUR.hARvFE1qrG5O0OVZBY3XIusIphN5Y96\",\n" +
                "            \"registration\": \"2018422234652\",\n" +
                "            \"name\": \"FRANCISCO MARIO FERREIRA\",\n" +
                "            \"phone\": \"85996522180\",\n" +
                "            \"parent\": \"CARLOS WAGNER FERREIRA\",\n" +
                "            \"phoneParent\": \"85993331268\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234652\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 66,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 33,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 29,\n" +
                "                \"title\": \"CORTANDO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 30,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"videoY.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 32,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 31,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:40:41.142+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"2018422231030\",\n" +
                "            \"password\": \"$2a$10$MfngT28Polzp6s.iCHWX0./bhgVEgItkO./EW.iy0saNIVE9YuYhS\",\n" +
                "            \"registration\": \"2018422231030\",\n" +
                "            \"name\": \"JOAO MOREIRA DA SILVA\",\n" +
                "            \"phone\": \"8592827382\",\n" +
                "            \"parent\": \"FRANCISCA MOREIRA DA SILVA\",\n" +
                "            \"phoneParent\": \"8881772736\",\n" +
                "            \"progress\": 1,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422231030\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 67,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 40,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 37,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 36,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.278+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"2018422231030\",\n" +
                "            \"password\": \"$2a$10$MfngT28Polzp6s.iCHWX0./bhgVEgItkO./EW.iy0saNIVE9YuYhS\",\n" +
                "            \"registration\": \"2018422231030\",\n" +
                "            \"name\": \"JOAO MOREIRA DA SILVA\",\n" +
                "            \"phone\": \"8592827382\",\n" +
                "            \"parent\": \"FRANCISCA MOREIRA DA SILVA\",\n" +
                "            \"phoneParent\": \"8881772736\",\n" +
                "            \"progress\": 1,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422231030\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 68,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 50,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"GARÇOM AVANÇADO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 46,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 48,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 47,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:44:09.897+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"2018422231030\",\n" +
                "            \"password\": \"$2a$10$MfngT28Polzp6s.iCHWX0./bhgVEgItkO./EW.iy0saNIVE9YuYhS\",\n" +
                "            \"registration\": \"2018422231030\",\n" +
                "            \"name\": \"JOAO MOREIRA DA SILVA\",\n" +
                "            \"phone\": \"8592827382\",\n" +
                "            \"parent\": \"FRANCISCA MOREIRA DA SILVA\",\n" +
                "            \"phoneParent\": \"8881772736\",\n" +
                "            \"progress\": 1,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422231030\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 70,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 40,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 37,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 36,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.278+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 57,\n" +
                "            \"email\": \"2018422234630\",\n" +
                "            \"password\": \"$2a$10$kgxUey4yryRbYfj9IxxoI.YLfS0xL2FV97PjonsF541DDZJtVff.6\",\n" +
                "            \"registration\": \"2018422234630\",\n" +
                "            \"name\": \"MANOEL DA SILVA BARBOSA\",\n" +
                "            \"phone\": \"85992833212\",\n" +
                "            \"parent\": \"MARIA DE LURDES BARBOSA\",\n" +
                "            \"phoneParent\": \"8881722345\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234630\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 69,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 50,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"GARÇOM AVANÇADO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 46,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 48,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 47,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:44:09.897+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 57,\n" +
                "            \"email\": \"2018422234630\",\n" +
                "            \"password\": \"$2a$10$kgxUey4yryRbYfj9IxxoI.YLfS0xL2FV97PjonsF541DDZJtVff.6\",\n" +
                "            \"registration\": \"2018422234630\",\n" +
                "            \"name\": \"MANOEL DA SILVA BARBOSA\",\n" +
                "            \"phone\": \"85992833212\",\n" +
                "            \"parent\": \"MARIA DE LURDES BARBOSA\",\n" +
                "            \"phoneParent\": \"8881722345\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234630\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 71,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 33,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 29,\n" +
                "                \"title\": \"CORTANDO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 30,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"videoY.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 32,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 31,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:40:41.142+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 57,\n" +
                "            \"email\": \"2018422234630\",\n" +
                "            \"password\": \"$2a$10$kgxUey4yryRbYfj9IxxoI.YLfS0xL2FV97PjonsF541DDZJtVff.6\",\n" +
                "            \"registration\": \"2018422234630\",\n" +
                "            \"name\": \"MANOEL DA SILVA BARBOSA\",\n" +
                "            \"phone\": \"85992833212\",\n" +
                "            \"parent\": \"MARIA DE LURDES BARBOSA\",\n" +
                "            \"phoneParent\": \"8881722345\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234630\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 72,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 57,\n" +
                "            \"email\": \"2018422234630\",\n" +
                "            \"password\": \"$2a$10$kgxUey4yryRbYfj9IxxoI.YLfS0xL2FV97PjonsF541DDZJtVff.6\",\n" +
                "            \"registration\": \"2018422234630\",\n" +
                "            \"name\": \"MANOEL DA SILVA BARBOSA\",\n" +
                "            \"phone\": \"85992833212\",\n" +
                "            \"parent\": \"MARIA DE LURDES BARBOSA\",\n" +
                "            \"phoneParent\": \"8881722345\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234630\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 73,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 33,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 29,\n" +
                "                \"title\": \"CORTANDO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 30,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"videoY.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 32,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 31,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:40:41.142+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 56,\n" +
                "            \"email\": \"2018422234545\",\n" +
                "            \"password\": \"$2a$10$NoVJxV6R2iW8fzloPOL0T.KUopmz9m8/nT5u0WmNuMMI0wygJV0Wi\",\n" +
                "            \"registration\": \"2018422234545\",\n" +
                "            \"name\": \"MARIA CARMEM DO ROSARIO\",\n" +
                "            \"phone\": \"85992338675\",\n" +
                "            \"parent\": \"JOEL GOMES DO ROSARIO\",\n" +
                "            \"phoneParent\": \"88981770087\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234545\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 76,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 40,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 37,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 36,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.278+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 56,\n" +
                "            \"email\": \"2018422234545\",\n" +
                "            \"password\": \"$2a$10$NoVJxV6R2iW8fzloPOL0T.KUopmz9m8/nT5u0WmNuMMI0wygJV0Wi\",\n" +
                "            \"registration\": \"2018422234545\",\n" +
                "            \"name\": \"MARIA CARMEM DO ROSARIO\",\n" +
                "            \"phone\": \"85992338675\",\n" +
                "            \"parent\": \"JOEL GOMES DO ROSARIO\",\n" +
                "            \"phoneParent\": \"88981770087\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234545\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 74,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 50,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"GARÇOM AVANÇADO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 46,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 48,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 47,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:44:09.897+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 56,\n" +
                "            \"email\": \"2018422234545\",\n" +
                "            \"password\": \"$2a$10$NoVJxV6R2iW8fzloPOL0T.KUopmz9m8/nT5u0WmNuMMI0wygJV0Wi\",\n" +
                "            \"registration\": \"2018422234545\",\n" +
                "            \"name\": \"MARIA CARMEM DO ROSARIO\",\n" +
                "            \"phone\": \"85992338675\",\n" +
                "            \"parent\": \"JOEL GOMES DO ROSARIO\",\n" +
                "            \"phoneParent\": \"88981770087\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234545\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 75,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 13,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 10,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 7,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.013+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 56,\n" +
                "            \"email\": \"2018422234545\",\n" +
                "            \"password\": \"$2a$10$NoVJxV6R2iW8fzloPOL0T.KUopmz9m8/nT5u0WmNuMMI0wygJV0Wi\",\n" +
                "            \"registration\": \"2018422234545\",\n" +
                "            \"name\": \"MARIA CARMEM DO ROSARIO\",\n" +
                "            \"phone\": \"85992338675\",\n" +
                "            \"parent\": \"JOEL GOMES DO ROSARIO\",\n" +
                "            \"phoneParent\": \"88981770087\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234545\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 26,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 16,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 12,\n" +
                "                \"level\": 3\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 15,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 14,\n" +
                "                        \"sets\": 4,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.018+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 77,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 44,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 5,\n" +
                "                \"level\": 2\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 43,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 42,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 10\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.280+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 78,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 41,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 12,\n" +
                "                \"level\": 3\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 39,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 38,\n" +
                "                        \"sets\": 4,\n" +
                "                        \"repeats\": 12\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.282+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 27,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 11,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 3,\n" +
                "                \"title\": \"BOTÕES\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 4,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"v3d.mp4\"\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 5,\n" +
                "                \"level\": 2\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 9,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 8,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 12\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:11:17.015+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 25,\n" +
                "            \"email\": \"2018422232112\",\n" +
                "            \"password\": \"$2a$10$T5Ps72aReXdAeFntv3gLY.neykXCbJxYWBMPxzxXRhKOB80wgJtnS\",\n" +
                "            \"registration\": \"2018422232112\",\n" +
                "            \"name\": \"FRANCISCO LUCAS RODRIGUES\",\n" +
                "            \"phone\": \"8592827262\",\n" +
                "            \"parent\": \"RAIMUNDA SOUSA RODRIGUES\",\n" +
                "            \"phoneParent\": \"8599763746\",\n" +
                "            \"progress\": 3,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422232112\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 97,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 95,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 88,\n" +
                "                \"title\": \"GARÇOM COM SACO DE FEIJÃO\",\n" +
                "                \"description\": \"DESCRIÇÃO DO EXERCICIO\",\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 89,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": \"Télégraphe de Chappe.mp4\"\n" +
                "                },\n" +
                "                \"objects\": [\n" +
                "                    {\n" +
                "                        \"id\": 87,\n" +
                "                        \"name\": \"PEÇA LEGO\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 92,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 90,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-05-04T13:56:42.123+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 57,\n" +
                "            \"email\": \"2018422234630\",\n" +
                "            \"password\": \"$2a$10$kgxUey4yryRbYfj9IxxoI.YLfS0xL2FV97PjonsF541DDZJtVff.6\",\n" +
                "            \"registration\": \"2018422234630\",\n" +
                "            \"name\": \"MANOEL DA SILVA BARBOSA\",\n" +
                "            \"phone\": \"85992833212\",\n" +
                "            \"parent\": \"MARIA DE LURDES BARBOSA\",\n" +
                "            \"phoneParent\": \"8881722345\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234630\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 98,\n" +
                "        \"locked\": true,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 41,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 34,\n" +
                "                \"title\": \"FORTALECIMENTO COM BOLA\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 35,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 12,\n" +
                "                \"level\": 3\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 39,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 38,\n" +
                "                        \"sets\": 4,\n" +
                "                        \"repeats\": 12\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:43:40.282+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 57,\n" +
                "            \"email\": \"2018422234630\",\n" +
                "            \"password\": \"$2a$10$kgxUey4yryRbYfj9IxxoI.YLfS0xL2FV97PjonsF541DDZJtVff.6\",\n" +
                "            \"registration\": \"2018422234630\",\n" +
                "            \"name\": \"MANOEL DA SILVA BARBOSA\",\n" +
                "            \"phone\": \"85992833212\",\n" +
                "            \"parent\": \"MARIA DE LURDES BARBOSA\",\n" +
                "            \"phoneParent\": \"8881722345\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234630\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 63,\n" +
                "        \"locked\": false,\n" +
                "        \"grasp\": {\n" +
                "            \"id\": 50,\n" +
                "            \"exercise\": {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"GARÇOM AVANÇADO\",\n" +
                "                \"description\": null,\n" +
                "                \"midia\": {\n" +
                "                    \"id\": 46,\n" +
                "                    \"pathVideo\": \"http://i9move.quixada.ufc.br/api/video/v3d.mp4\",\n" +
                "                    \"pathTitle\": null\n" +
                "                },\n" +
                "                \"objects\": []\n" +
                "            },\n" +
                "            \"level\": {\n" +
                "                \"id\": 6,\n" +
                "                \"level\": 1\n" +
                "            },\n" +
                "            \"recommendation\": {\n" +
                "                \"id\": 48,\n" +
                "                \"serie\": [\n" +
                "                    {\n" +
                "                        \"id\": 47,\n" +
                "                        \"sets\": 3,\n" +
                "                        \"repeats\": 8\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            \"tip\": null,\n" +
                "            \"latestUpdate\": \"2018-04-23T02:44:09.897+0000\",\n" +
                "            \"sequence\": 0\n" +
                "        },\n" +
                "        \"patient\": {\n" +
                "            \"id\": 58,\n" +
                "            \"email\": \"2018422234652\",\n" +
                "            \"password\": \"$2a$10$1k2pTfvij9jUbWfkijhUR.hARvFE1qrG5O0OVZBY3XIusIphN5Y96\",\n" +
                "            \"registration\": \"2018422234652\",\n" +
                "            \"name\": \"FRANCISCO MARIO FERREIRA\",\n" +
                "            \"phone\": \"85996522180\",\n" +
                "            \"parent\": \"CARLOS WAGNER FERREIRA\",\n" +
                "            \"phoneParent\": \"85993331268\",\n" +
                "            \"progress\": 0,\n" +
                "            \"responsible\": null,\n" +
                "            \"enabled\": true,\n" +
                "            \"accountNonExpired\": true,\n" +
                "            \"accountNonLocked\": true,\n" +
                "            \"credentialsNonExpired\": true,\n" +
                "            \"authorities\": [],\n" +
                "            \"username\": \"2018422234652\"\n" +
                "        }\n" +
                "    }\n" +
                "]";
    }

}


