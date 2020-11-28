node {
    def dockerImage
    stage('Clone repository') {
        checkout scm
    }
    stage('Build image') {
        dockerImage = docker.build("alexanderwyss/sonos-assistant-tts")
    }
    stage('Deploy') {
        sh 'docker stop sonos-assistant-tts || true && docker rm -f sonos-assistant-tts || true'
        withCredentials([string(credentialsId: 'SPEECH_KEY', variable: 'key'), string(credentialsId: 'SPEECH_REGION', variable: 'region')]) {
            sh 'docker run -d --expose 8080 --restart unless-stopped --name sonos-assistant-tts -v /docker/tts:/tts -e AUDIO_PATH=/tts -e SPEECH_KEY=$key -e SPEECH_REGION=$region -e PORT=8080 -e VIRTUAL_HOST=tts.wyss.tech -e VIRTUAL_PORT=8080 -e LETSENCRYPT_HOST=tts.wyss.tech alexanderwyss/sonos-assistant-tts:latest'
        }
    }
}
