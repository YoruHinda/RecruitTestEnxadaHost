<h1 align="center">Teste De Recrutamento EnxadaHost</h1>

Olá enxada, desenvolvi o código da seguinte maneira:<br/>
Criei alguns comandos para o teste estara no documento todos os comandos com sua finalidade.<br/>
Alem dos comandos utilizei o config.yml do spigot se o usuario optar por configurar por este método.<br/>
Para acessar o banco de dados precisa ser acessado pelo docker exec.<br/>
Pode ser que algumas das funcionalidade do wind charge não funcione já que na api do spigot essa
funcionalidade esta em estado experimental.

obs: tenho costume de escrever a documentação dos meus projetos em inglês, mas pensando
na pessoa que vai avaliar meu código, a documentação estara toda em português.

## Ferramentas
- JDK 22
- Docker
- MySql
- Spigot 1.21

## Como usar
```
Clone Git repository
$ git clone https://github.com/YoruHinda/RecruitTestEnxadaHost.git
```

```
Inicie o docker-compose
$ docker-compose up
```

```
Acesse o servidor local de minecraft pela porta
127.0.0.1:25565 / localhost:25565
```
## Principais comandos
### /home
 `Volta pra home padrão!`
### /home {homename}
 `Volta pra uma home especifica que o jogador nomeou!`
### /sethome
 `Seta a home principal!`
### /sethome {homename}
 `Seta uma home customizada com nome que jogador preferir!`
### /delhome
`Deleta a home principal!`
### /delhome {homename}
`Deleta a home customizada!`
### /homes
`Lista todas as homes!`
### /wind set velocity {level}
 `Altera a volocidade do projetil e a força desejada!`
### /wind set force {level}
 `Altera a força de impulso do wind charger!`
### /wind set particles {true/false} {particle name}
 `Desativa ou Ativa uma particula desejada!`

## Configuração pelo YML

Abrir a pasta `data` e ir no arquivo config.

### config.yml
```
# ativa e desativa cooldown
cooldown: true
# Define o tempo
cooldown-time: 12
# ativa e desativa as particulas de teleport
teleport-particle: true
# Define a particula de teleporte
teleport-particle-effect: PORTAL

# Define a volocidade de projetil
wind-velocity: 2
# Define a força da explosão
wind-force: 1
# ativa e desativa as particulas da explosão de vento
wind-particles: true
# Define a particula da explosão
wind-particles-effect: FIREWORK
```
