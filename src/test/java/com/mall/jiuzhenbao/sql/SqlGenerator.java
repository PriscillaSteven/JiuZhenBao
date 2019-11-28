package com.mall.jiuzhenbao.sql;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title SqlGenerator
 * @Description	根据JAVA实体生成SQL建表语句工具
 */
public class SqlGenerator {
    public static Map<String, String> property2SqlColumnMap = new HashMap<>();

    static {
        property2SqlColumnMap.put("int", "INT");
        property2SqlColumnMap.put("short", "tinyint");
        property2SqlColumnMap.put("long", "bigint");
        property2SqlColumnMap.put("bigdecimal", "decimal(19,2)");
        property2SqlColumnMap.put("double", "double precision not null");
        property2SqlColumnMap.put("float", "float");
        property2SqlColumnMap.put("boolean", "bit");
        property2SqlColumnMap.put("timestamp", "datetime");
        property2SqlColumnMap.put("date", "datetime");
        property2SqlColumnMap.put("string", "VARCHAR(500)");
    }


    public static String generateSql(String className,String tableName, String primaryKey, String filePath){
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            Field[] fields = clz.getDeclaredFields();
            StringBuffer column = new StringBuffer();
            for (Field f : fields) {
                if (f.getName().equals(primaryKey)){
                    continue;
                }
                //column.append(" \n `"+f.getName()+"`").append(varchar);
                column.append(getColumnSql(f));
            }
            String sqlPrimaryKey = StringUtils.camel2Underline(primaryKey).toUpperCase();
            StringBuffer sql = new StringBuffer();
            sql.append("\n DROP TABLE IF EXISTS `"+tableName+"`; ")
                    .append(" \n CREATE TABLE `"+tableName+"`  (")
                    .append(" \n `"+sqlPrimaryKey+"` bigint(20) NOT NULL AUTO_INCREMENT,")
                    .append(" \n "+column)
                    .append(" \n PRIMARY KEY (`"+sqlPrimaryKey+"`)")
                    .append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
            String sqlText = sql.toString();
            StringToSql(sqlText,filePath);
            return sqlText;
        } catch (ClassNotFoundException e) {
//            log.debug("SQL生成异常：",e);
            e.printStackTrace();
            return null;
        }
    }

    private static String getColumnSql(Field field){
        String tpl = "\n `%s` %s DEFAULT NULL,";
        String typeName = field.getType().getSimpleName().toLowerCase();
        String sqlType = property2SqlColumnMap.get(typeName);
        if (sqlType == null || sqlType.isEmpty()){
//            log.info(field.getName() + ":"+field.getType().getName()+" 需要单独创建表");
            System.out.println(field.getName() + ":"+field.getType().getName()+" 需要单独创建表");
            return "";
        }
        String column =StringUtils.camel2Underline(field.getName()).toUpperCase();
        String sql = String.format(tpl,column,sqlType.toUpperCase());
        return sql;
    }
    private static void StringToSql(String str,String path){
        byte[] sourceByte = str.getBytes();
        if(null != sourceByte){
            try {
                File file = new File(path);
                if (!file.exists()) {
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(sourceByte);
                outStream.flush();
                outStream.close();
                System.out.println("生成成功");
            } catch (Exception e) {
//                log.debug("保存SQL文件异常：",e);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        String[] classes = new String[]{"com.mall.jiuzhenbao.user.domain.User", "com.mall.jiuzhenbao.address.domain.Address", "com.mall.jiuzhenbao.bankcard.domain.BankCard", "com.mall.jiuzhenbao.coupon.domain.Coupon", "com.mall.jiuzhenbao.delivery.domain.Delivery", "com.mall.jiuzhenbao.goods.domain.Goods", "com.mall.jiuzhenbao.income.domain.Income", "com.mall.jiuzhenbao.order.domain.Order"};
        generateSql("com.mall.jiuzhenbao.user.domain.User", "user", "user_id", "D:\\gaowei\\Ebook\\sql\\user.txt");
        generateSql("com.mall.jiuzhenbao.address.domain.Address", "user", "address_id", "D:\\gaowei\\Ebook\\sql\\address.txt");
        generateSql("com.mall.jiuzhenbao.bankcard.domain.BankCard", "bank_card", "card_id", "D:\\gaowei\\Ebook\\sql\\bankcard.txt");
        generateSql("com.mall.jiuzhenbao.coupon.domain.Coupon", "coupon", "user_id", "D:\\gaowei\\Ebook\\sql\\coupon.txt");
        generateSql("com.mall.jiuzhenbao.delivery.domain.Delivery", "delievery", "delivery_id", "D:\\gaowei\\Ebook\\sql\\delivery.txt");
        generateSql("com.mall.jiuzhenbao.goods.domain.Goods", "goods", "goods", "D:\\gaowei\\Ebook\\sql\\goods.txt");
        generateSql("com.mall.jiuzhenbao.income.domain.Income", "income", "income", "D:\\gaowei\\Ebook\\sql\\income.txt");
        generateSql("com.mall.jiuzhenbao.order.domain.Order", "order", "order_id", "D:\\gaowei\\Ebook\\sql\\order.txt");
    }
}
