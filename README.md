<h1 align="center">Teste De Recrutamento EnxadaHost</h1>

Olá enxada, desenvolvi o código da seguinte maneira:<br/>
Criei 3 comandos para o teste /wind, /sethome e /home.<br/>
Alem dos comandos utilizei o config.yml do spigot se o usuario optar por configurar por este método.

pasta /data contem a config.yaml que sera enviada diretamente pra pasta dentro do container docker.

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
$ git clone 
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
### /wind set velocity {level}
 `Altera a volocidade do projetil e a força desejada!`
### /wind set force {level}
 `Altera a força de impulso do wind charger!`
### /wind set particles {on/off} {particle name}
 `Desativa ou Ativa uma particula desejada!`

## Configuração pelo YML

### config.yml
```
windforce:
windvelocity:
windparticles:

homeparticles: true / false
```
