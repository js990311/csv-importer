package com.csv.importer.yaml.service;

import com.csv.importer.yaml.dto.Column;
import com.csv.importer.yaml.dto.Work;
import org.springframework.stereotype.Component;

@Component
public class ImportQueryBuilder {
    public String importSql(Work work){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(work.getTableName());
        sb.append("(");
        for(int i=0;i<work.getColumns().size();i++){
            sb.append(work.getColumns().get(i).getName());
            if(i+1 != work.getColumns().size()){
                sb.append(", ");
            }
        }
        sb.append(") VALUES (");
        for(int i=0;i<work.getColumns().size();i++){
            if(i+1 != work.getColumns().size()){
                sb.append("?, ");
            }else {
                sb.append("?");
            }
        }
        sb.append(");");

        return sb.toString();
    }

    private void addColumns(Column column, StringBuilder sb){

        sb.append(",");
    }
}
