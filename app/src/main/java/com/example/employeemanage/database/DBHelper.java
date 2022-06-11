package com.example.employeemanage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.employeemanage.model.Account;
import com.example.employeemanage.model.Employee;
import com.example.employeemanage.model.Order;
import com.example.employeemanage.model.OrderDetail;
import com.example.employeemanage.model.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "EmployeeManage_DB.db";
    public static final int DB_VERSION = 27;
    public static final String TABLE_NAME_ACCOUNT = "Account";
    public static final String TABLE_NAME_EMPLOYEE = "Employee";
    public static final String TABLE_NAME_PRODUCT = "Product";
    public static final String TABLE_NAME_ORDERS = "Orders";
    public static final String TABLE_NAME_ORDERS_DETAIL = "Orders_detail";


    private static final String id = "id";
    // ----------------------------------ACCOUNT----------------------------------------------
    private static final String username = "username";
    private static final String password = "password";
    private static final String fullName = "fullName";
    private static final String email = "email";
    private static final String kind = "kind";
    private static final String phone = "phone";
    private static final String avatar = "avatar";

    // ----------------------------------EMPLOYEE----------------------------------------------
    private static final String birthday = "birthday";
    private static final String accountId = "accountId";
    private static final String address = "address";
    private static final String gender = "gender";
    private static final String identityNumber = "identityNumber";
    private static final String bankNo = "bankNo";
    private static final String bankName = "bankName";
    private static final String salary = "salary";
    private static final String position = "position";
    private static final String dateTime = "dateTime";
    private static final String hours = "hours";

    // ----------------------------------PRODCUT----------------------------------------------
    private static final String name = "name";
    private static final String price = "price";
    private static final String saleOff = "saleOff";
    private static final String description = "description";

    // ----------------------------------ORDERS----------------------------------------------
    private static final String totalMoney = "totalMoney";
    private static final String timeOrder = "timeOrder";
    private static final String employeeId = "employeeId";
    private static final String customerName = "customerName";
    private static final String customerPhone = "customerPhone";

    // ----------------------------------ORDERS DETAIL----------------------------------------------
    private static final String productId = "productId";
    private static final String ordersId = "ordersId";
    private static final String quantity = "quantity";


    private static final String CREATE_ACCOUNT_TABLE = "CREATE TABLE account(" +
            id + " integer primary key AUTOINCREMENT," +
            username + " NVARCHAR(100) NOT NULL," +
            password + " NVARCHAR(100) NOT NULL," +
            fullName + " NVARCHAR(100) NOT NULL," +
            email + " NVARCHAR(100) NOT NULL," +
            kind + " integer NOT NULL," +
            phone + " NVARCHAR(15) NOT NULL," +
            avatar + " BLOB)";


//    bankNo + " NVARCHAR(100)," +
//    bankName + " NVARCHAR(100)," +
    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE employee(" +
            id + " integer primary key AUTOINCREMENT," +
            birthday + " NVARCHAR(10) NOT NULL," +
            position + " integer NOT NULL," +
            accountId + " integer NOT NULL," +
            address + " NVARCHAR(100) NOT NULL," +
            gender + " integer NOT NULL," +
            identityNumber + " NVARCHAR(100) NOT NULL," +
            salary + " float NOT NULL," +
            dateTime + " NVARCHAR(100)," +
            hours + " float," +
            "FOREIGN KEY (" + accountId + ") REFERENCES Account(id))";

    private static final String CREATE_PRODUCT_TABLE = "CREATE TABLE product(" +
            id + " integer primary key AUTOINCREMENT," +
            name + " NVARCHAR(100) NOT NULL," +
            price + " float NOT NULL," +
            description + " TEXT," +
            saleOff + " Integer NOT NULL)";

    private static final String CREATE_ORDERS_TABLE = "CREATE TABLE orders(" +
            id + " integer primary key AUTOINCREMENT," +
            timeOrder + " NVARCHAR(100) NOT NULL," +
            totalMoney + " float NOT NULL," +
            customerName + " NVARCHAR(100)," +
            customerPhone + " NVARCHAR(11)," +
            employeeId + " integer NOT NULL," +
            "FOREIGN KEY (" + employeeId + ") REFERENCES Employee(id))";

    private static final String CREATE_ORDERS_DETAIL_TABLE = "CREATE TABLE orders_detail(" +
            id + " integer primary key AUTOINCREMENT," +
            quantity + " integer NOT NULL," +
            productId + " integer not null," +
            ordersId + " integer not null," +
            "FOREIGN KEY (" + productId + ") REFERENCES Product(id)," +
            "FOREIGN KEY (" + ordersId + ") REFERENCES Orders(id))";

    private Context context;
    public DBHelper(Context content) {
        super(content,DATABASE_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TABLE);
        db.execSQL(CREATE_EMPLOYEE_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_ORDERS_TABLE);
        db.execSQL(CREATE_ORDERS_DETAIL_TABLE);
        //Khởi tạo dữ liệu
        createDefaultAccountData(db);
        createDefaultEmployeeData(db);
        createDefaultProductData(db);
        createDefaultOrdersData(db);
        createDefaultOrdersDetailData(db);
    }
    //Account
    public void createDefaultAccountData(SQLiteDatabase db) {
        //Account-admin
        db.execSQL("INSERT INTO Account(username,password,fullName,email,kind,phone) VALUES('admin','admin','Admin','admin@gmail.com',1,'0934191232')");
        //Account-employee
        db.execSQL("INSERT INTO Account(username,password,fullName,email,kind,phone) VALUES('khanhvan','123','Khanh Van','van@gmail.com',0,'0934164613')");
        db.execSQL("INSERT INTO Account(username,password,fullName,email,kind,phone) VALUES('huyhoa','123','Huy Hoa','hoa@gmail.com',0,'0934112312')");
        db.execSQL("INSERT INTO Account(username,password,fullName,email,kind,phone) VALUES('ducduy','123','Duc Duy','duy@gmail.com',0,'0912312323')");
    }
    //Employee
    public void createDefaultEmployeeData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Employee(birthday,position,accountId,address,gender,identityNumber,dateTime,salary,hours) VALUES('11/01/2001',2,2,'Tien Giang',1,'312999101','05:1:00',3,2)");
        db.execSQL("INSERT INTO Employee(birthday,position,accountId,address,gender,identityNumber,salary,hours) VALUES('29/02/2001',1,3,'TPHCM',1,'312666101',1.5,1)");
        db.execSQL("INSERT INTO Employee(birthday,position,accountId,address,gender,identityNumber,dateTime,salary,hours) VALUES('30/04/2001',0,4,'DakLak',1,'312555101','14:30:00',4.5,3)");
    }
    //Product
    public void createDefaultProductData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Product(name,price,saleOff) VALUES('SamSung S22',2000,0)");
        db.execSQL("INSERT INTO Product(name,price,saleOff) VALUES('Iphone 13 Pro Max',2500,0)");
        db.execSQL("INSERT INTO Product(name,price,saleOff) VALUES('Nokia',200,0)");
        db.execSQL("INSERT INTO Product(name,price,saleOff) VALUES('Vivo',300,10)");
        db.execSQL("INSERT INTO Product(name,price,saleOff) VALUES('Iphone 12',1500,0)");
    }
    //Orders
    public void createDefaultOrdersData(SQLiteDatabase db) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);

        db.execSQL("INSERT INTO Orders(totalMoney,timeOrder,employeeId,customerName,customerPhone) VALUES(500,'"+ strDate +"',1,'Khanh Van','0132539862')");
        db.execSQL("INSERT INTO Orders(totalMoney,timeOrder,employeeId,customerName,customerPhone) VALUES(2200,'"+ strDate +"',2,'Duc Duy','0394834722')");
        db.execSQL("INSERT INTO Orders(totalMoney,timeOrder,employeeId,customerName,customerPhone) VALUES(1500,'"+ strDate +"',3,'Huy Hoa','0238474735')");

        db.execSQL("INSERT INTO Orders(totalMoney,timeOrder,employeeId,customerName,customerPhone) VALUES(2500,'10-5-2022 15:00:00',1,'Van','0809762633')");
        db.execSQL("INSERT INTO Orders(totalMoney,timeOrder,employeeId,customerName,customerPhone) VALUES(200,'06-5-2022 16:00:00',2,'Hoa','0947471829')");
        db.execSQL("INSERT INTO Orders(totalMoney,timeOrder,employeeId,customerName,customerPhone) VALUES(1500,'08-4-2022 13:00:00',3,'Duy','0123848483')");
    }
    //Orders Detail
    public void createDefaultOrdersDetailData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(2,4,1)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(3,1,1)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(4,1,2)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(1,2,1)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(3,2,1)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(5,3,1)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(3,5,1)");
        db.execSQL("INSERT INTO orders_detail(productId,ordersId,quantity) VALUES(5,6,1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_ACCOUNT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_EMPLOYEE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_PRODUCT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_ORDERS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME_ORDERS_DETAIL + "'");
        onCreate(db);
    }

    private ContentValues getValueAccount(Account account) {
        ContentValues values = new ContentValues();
        values.put(username,account.getUserName());
        values.put(password,account.getPassWord());
        values.put(fullName,account.getFullName());
        values.put(email,account.getEmail());
        values.put(kind,account.getKind());
        values.put(phone,account.getPhone());
        values.put(avatar,account.getAvatar());
        return values;
    }

    public Long createAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueAccount(account);

        Long id = db.insert(TABLE_NAME_ACCOUNT,null,values);
        db.close();
        return id;
    }

    public ArrayList<Account> searchAccount(String keyword){
        ArrayList<Account> accountList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ACCOUNT + " WHERE fullname LIKE '%" + keyword + "%' OR id LIKE '%" + keyword + "%' OR phone LIKE '%" + keyword + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Account account = new Account(cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getInt(5),
                        cursor.getString(6),cursor.getBlob(7));
                account.setId(cursor.getLong(0));
                accountList.add(account);
            }while (cursor.moveToNext());
        }
        db.close();
        return accountList;
    }

    public ArrayList<Account> getAllAccount() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_ACCOUNT, null);

        ArrayList<Account> accountArrayList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Account account =new Account(cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getInt(5),
                        cursor.getString(6),cursor.getBlob(7));
                account.setId(cursor.getLong(0));
                accountArrayList.add(account);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return accountArrayList;
    }

    public Account findAccountByUsername(String usernameAccount){
        Account account = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_ACCOUNT + " WHERE " + username + "= '" + usernameAccount + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() || cursor.getCount() == 0){
            return null;
        }else {
            cursor.moveToFirst();
            account = new Account(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getInt(5),
                    cursor.getString(6),cursor.getBlob(7));
            account.setId(cursor.getLong(0));
        }
        db.close();
        return account;
    }

    public long updateAccount(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueAccount(account);

        int count = db.update(TABLE_NAME_ACCOUNT,values, id + "=?"
                ,new String[]{String.valueOf(account.getId())});
        db.close();
        return count;
    }

    public void deleteAccount(String idAccount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_ACCOUNT, "id=?", new String[]{idAccount});
        db.close();
    }

    //----------------------------Employee--------------------------------------
    private ContentValues getValueEmployee(Employee employee) {
        ContentValues values = new ContentValues();
        values.put(birthday,employee.getBirthday());
        values.put(accountId, employee.getAccountId());
        values.put(address, employee.getAddress());
        values.put(gender, employee.getGender());
        values.put(position, employee.getPosition());
        values.put(identityNumber, employee.getIdentityNumber());
//        values.put(bankNo, employee.getBankNo());
//        values.put(bankName, employee.getBankName());
        values.put(salary, employee.getSalary());
        values.put(dateTime, employee.getDateTime());
        values.put(hours, employee.getHours());
        return values;
    }

    public Long createEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueEmployee(employee);

        Long id = db.insert(TABLE_NAME_EMPLOYEE,null,values);
        db.close();
        return id;
    }

    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> employeeList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_EMPLOYEE + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Employee employee = new Employee(cursor.getString(1) ,cursor.getInt(2),
                        cursor.getLong(3),cursor.getString(4),cursor.getInt(5),
                        cursor.getString(6),cursor.getDouble(7),cursor.getString(8),cursor.getDouble(9));
                employee.setId(cursor.getLong(0));
                employeeList.add(employee);
            }while (cursor.moveToNext());
        }
        db.close();
        return employeeList;
    }

    public Employee findEmployeeByAccountId(String idAccount){
        Employee employee = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_EMPLOYEE + " WHERE " + accountId + " = '" + idAccount + "';";
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() || cursor.getCount() == 0){
            return null;
        }else {
            cursor.moveToFirst();
            employee = new Employee(cursor.getString(1) ,cursor.getInt(2),
                    cursor.getLong(3),cursor.getString(4),cursor.getInt(5),
                    cursor.getString(6),cursor.getDouble(7),cursor.getString(8),cursor.getDouble(9));
            employee.setId(cursor.getLong(0));
        }
        db.close();
        return employee;
    }

    public long updateEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueEmployee(employee);

        int count = db.update(TABLE_NAME_EMPLOYEE,values, id + "=?"
                ,new String[]{String.valueOf(employee.getId())});
        db.close();
        return count;
    }

    public void setHoursEmployee(){
        String query = "UPDATE " + TABLE_NAME_EMPLOYEE + " SET " + hours + " = 0;";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public void updateHoursEmployee(String idEmployee, String time){
        ContentValues values=new ContentValues();
        values.put(dateTime,time);

        //String query = "UPDATE " + TABLE_NAME_EMPLOYEE + " SET " + dateTime + " =?" + time +" WHERE "+accountId+ " =?"+id+";";
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME_EMPLOYEE,values, id + "=?"
                ,new String[]{idEmployee});
        db.close();
        //db.execSQL(query);
    }

    public int deleteEmployee(String employeeId){
        SQLiteDatabase db = this.getWritableDatabase();
        int count = db.delete(TABLE_NAME_EMPLOYEE,id + "=?"
                , new String[]{String.valueOf(employeeId)});
        db.close();
        return count;
    }

    //--------------------------------------------------Product-------------------------------------------------------
    private ContentValues getValueProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(name,product.getName());
        values.put(price, product.getPrice());
        values.put(saleOff, product.getSaleOff());
        values.put(description, product.getDescription());
        return values;
    }

    public Long createProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueProduct(product);

        Long id = db.insert(TABLE_NAME_PRODUCT,null,values);
        db.close();
        return id;
    }

    public Product findProductById(Long productId){
        Product product = null;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_PRODUCT + " WHERE " + id + "= '" + productId + "'";
        Cursor cursor = db.rawQuery(query,null);
        if(!cursor.moveToFirst() || cursor.getCount() == 0){
            return null;
        }else {
            cursor.moveToFirst();
            product = new Product(cursor.getString(1),cursor.getDouble(2),
                    cursor.getString(3),cursor.getInt(4));
            product.setId(cursor.getLong(0));
        }
        db.close();
        return product;
    }

    // ------------------------------------------------Orders----------------------------------------------------
    private ContentValues getValueOrders(Order orders) {
        ContentValues values = new ContentValues();
        values.put(totalMoney,orders.getTotalMoney());
        values.put(timeOrder, orders.getTimeOrder());
        values.put(employeeId, orders.getEmployeeId());
        values.put(customerName, orders.getCustomerName());
        values.put(customerPhone, orders.getCustomerPhone());
        return values;
    }

    public List<Order> searchOrders(String keyword){
        List<Order> ordersList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ORDERS + " WHERE employeeId LIKE '%" + keyword + "%' OR id LIKE '%" + keyword + "%' OR customerName LIKE '%" + keyword + "%'" +
                " OR customerPhone LIKE '%" + keyword + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Order orders = new Order(cursor.getString(1),cursor.getDouble(2),
                        cursor.getString(3),cursor.getString(4),cursor.getLong(5));
                orders.setId(cursor.getLong(0));
                ordersList.add(orders);
            }while (cursor.moveToNext());
        }
        db.close();
        return ordersList;
    }

    public Long createOrders(Order orders){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueOrders(orders);

        Long id = db.insert(TABLE_NAME_ORDERS,null,values);
        db.close();
        return id;
    }

    public List<Order> getAllOrdersByTimeOrder(String currentTime){
        List<Order> ordersList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ORDERS + " WHERE " + timeOrder + " like '" + currentTime + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Order orders = new Order(cursor.getString(1),cursor.getDouble(2),
                        cursor.getString(3),cursor.getString(4),cursor.getLong(5));
                orders.setId(cursor.getLong(0));
                ordersList.add(orders);
            }while (cursor.moveToNext());
        }
        db.close();
        return ordersList;
    }

    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> ordersList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ORDERS + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Order orders = new Order(cursor.getString(1),cursor.getDouble(2),
                        cursor.getString(3),cursor.getString(4),cursor.getLong(5));
                orders.setId(cursor.getLong(0));
                ordersList.add(orders);
            }while (cursor.moveToNext());
        }
        db.close();
        return ordersList;
    }

    //----------------------------------------------Orders detail ------------------------------------------
    private ContentValues getValueOrdersDetail(OrderDetail orderDetail) {
        ContentValues values = new ContentValues();
        values.put(productId, orderDetail.getProductId());
        values.put(ordersId, orderDetail.getOrdersId());
        values.put(quantity, orderDetail.getQuantity());
        return values;
    }

    public Long createOrdersDetail(OrderDetail orderDetail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = getValueOrdersDetail(orderDetail);

        Long id = db.insert(TABLE_NAME_ORDERS_DETAIL,null,values);
        db.close();
        return id;
    }

    public ArrayList<OrderDetail> getAllOrdersDetailByOrdersId(Long idOrders){
        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_ORDERS_DETAIL + " WHERE " + ordersId + "= " + idOrders;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                OrderDetail orderDetail = new OrderDetail(cursor.getInt(1),cursor.getLong(2),
                        cursor.getLong(3));
                orderDetail.setId(cursor.getLong(0));
                orderDetailList.add(orderDetail);
            }while (cursor.moveToNext());
        }
        db.close();
        return orderDetailList;
    }
}
