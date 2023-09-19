import {exec} from 'child_process';
let currentPath = process.cwd();
let command = 'mvn clean install';
//执行命令,实时打印输出
exec(command, {cwd: currentPath}, function (error, stdout, stderr) {
    if (error) {
        console.error('error: ' + error);
        return;
    }
    console.log('stdout: ' + stdout);
    console.log('stderr: ' + typeof stderr);
    if (typeof stderr == 'string') {
        console.log('stderr: ' + stderr);
    }
});