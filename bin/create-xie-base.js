#!/usr/bin/env node
let spawn = require('child_process').spawn;
let path = require('path');
let rootPath = path.resolve(__dirname, '../');
//cd xie-base
process.chdir(rootPath);

const mvn = spawn('mvn', ['clean', 'install']);
let message = '';
const log = (buffer) => {
    let msg = buffer.toString();
    message += msg;
    if (msg.endsWith('\n')) {
        console.log(message.substring(0, message.length - 1));
        message = '';
    }
}
mvn.stdout.on('data', log);
mvn.stderr.on('data', log);
mvn.on('close', () => console.log(`安装完成`));
console.log('正在安装，请稍后...')