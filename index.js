import excel from 'node-xlsx';
import fs from 'fs';

const obj = excel.parse('./data/data1.ods');
const queryKelurahan = obj[obj.length - 1].data;
const fileName = 'MKelurahanSeeder';
const fileTemplate = fs.readFileSync('./template/template.java', 'utf-8');
let dataInserted = 0;
let fileIndex = 1;

for (let i = 0; i <= queryKelurahan.length; i+= 1000) {
  const dataToInsert = queryKelurahan.slice(i, i+1000);
  let res = '';

  for (const data of dataToInsert) {
    res += `\t  ${data[0]}\n`;
    dataInserted++;
  }
  
  console.log('1k data dimasukkan!');
  const data = fileTemplate
    .replace(/{{filename}}/g, `${fileName}${fileIndex}`)
    .replace('{{queryGoesHere}}', res);

  fs.writeFileSync(`./output/${fileName}${fileIndex}.java`, data, 'utf-8');
  fileIndex++;
}

console.log(`total: ${dataInserted}`);
