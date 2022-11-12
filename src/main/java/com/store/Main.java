package com.store;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main{
    // 所有用户
    private  static ArrayList<User> users = new ArrayList<User>();
    private static Scanner input = new Scanner(System.in);

    // 所有商品
    private static ArrayList<Good> goods = new ArrayList<Good>();

    // 订单
    private static ArrayList<Order> orders = new ArrayList<Order>();
    private static int orderId=0;


    // 订单项
    private static ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
    private static int orderItemId=0;


    // 购物车
    private static ArrayList<CarItem> car = new ArrayList<CarItem>();
    private static int carItemId=0;

    private static User loginUser;

    public static void main(String[] args) throws Exception {
        // 加载商品
        loadGoods();
        // 加载用户数据
        loadUser();
        System.out.println("======欢迎使用在线商城=======");
        while(true){
            System.out.println("1:登录 2:注册");
            System.out.print("请选择:");
            String choose=input.nextLine();
            switch(choose){
                case "1":
                    login();
                    break;
                case "2":
                   registry();
                    break;
                default:
                    System.out.println("输入有误!请重新选择!");
                    break;
            }

        }
    }

    private static void registry() throws Exception {
        System.out.print("请输入用户名:");
        String userName=input.nextLine();
        System.out.print("请输入密码:");
        String password=input.nextLine();
        boolean flag=false;
        for(User user : users){
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)){
                flag=true;
                break;
            }
        }
        if(flag){
            System.out.println("该用户已存在!");
        }else{
            System.out.println("注册成功!");
            User user = new User(userName,password,0);
            loginUser=user;
            users.add(user);
            storeHome();
        }

    }

    /**
     * 用户登录
     */
    private static void login() throws Exception {
        System.out.print("请输入用户名:");
        String userName=input.nextLine();
        System.out.print("请输入密码:");
        String password=input.nextLine();
        boolean flag=false;
        for(User user : users){
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)){
                flag=true;
                loginUser=user;
                break;
            }
        }
        if(flag){
            System.out.println("登录成功!");
            storeHome();
        }else{
            System.out.println("用户名或密码错误!");
        }

    }

    /**
     * 购物首页
     */
    private static void storeHome() throws Exception {
        System.out.println("欢迎进入在线购物商城!");
        while(true){
            showHomeMenu();
            System.out.print("请选择:");
            String choose=input.nextLine();
            switch(choose){
                case "1":
                    showGoods();
                    break;
                case "2":
                    shoppingCar();
                    break;
                case "3":
                    shoppingInfo();
                    break;
                case "4":
                    balanceManager();
                    break;
                case "5":
                    System.out.println("系统已退出！欢迎下次使用!");
                    saveUser();
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误!请重新输入!");

            }
        }
    }


    private static void saveUser() throws Exception {
        for(User user :users){
            if(user.getUsername().equals(loginUser.getUsername())){
                user=loginUser;
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("src/main/resourse/Users.txt"));
        for(User user :users){
            pw.write(user.getUsername()+" "+user.getPassword()+" "+user.getBalance()+"\n");
        }
        PrintWriter pw2 = new PrintWriter(new FileWriter("src/main/resourse/goods.txt"));
        for(Good good :goods){
            pw2.write(good.getGid()+" "+good.getName()+" "+good.getPrice()+" "+good.getNums()+" "+good.getDesc()+"\n");
        }
        pw.close();
        pw2.close();


    }

    /**
     * 用户约查询和充值
     *
     */
    private static void balanceManager() {
        while(true){
            System.out.println("1:展示账户余额 2:余额充值 3:返回商城首页");
            System.out.print("请选择:");
            String choose = input.nextLine();
            boolean flag=false;
            switch(choose){
                case "1":
                    System.out.println("当前账户余额"+loginUser.getBalance());
                    break;
                case "2":
                    System.out.print("请输入要充值的金额:");
                    int addMoney=input.nextInt();
                    input.nextLine();
                    loginUser.setBalance(loginUser.getBalance()+addMoney);
                    System.out.println("充值成功");
                    break;
                case "3":
                    flag=true;
                    break;
                default:
                    System.out.println("输入错误!请重新输入!");
                    break;
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 购物记录
     */
    private static void shoppingInfo() {
        for(Order order:orders){
            System.out.println("订单号:"+order.getOid());
            for(OrderItem orderItem:orderItems){
                if(order.getOid()==orderItem.getOid()){
                    for(Good good:goods){
                        if(orderItem.getGid()==good.getGid()){
                            System.out.println("订单项编号:"+orderItem.getItemId()+" 商品名称:"+good.getName()+" 商品价格:"+good.getPrice()+" 购买数目:"+orderItem.getCount());
                        }
                    }
                }
            }
            System.out.println("总计:"+order.getTotal());
            System.out.println();
        }
    }

    /**
     * 购物车
     */
    private static void shoppingCar() {
        while(true){
            System.out.println("1:打印购物车商品 2:删除购物车内商品 3:购买结算 4:返回首页");
            System.out.print("请选择:");
            String choose=input.nextLine();
            boolean flagTotal=false;
            switch(choose){
                case "1":
                    double total=0;
                    for(CarItem carItem : car){
                        for(Good good :goods){
                            if(carItem.getGid()==good.getGid()){
                                System.out.println("购物车条目编号:"+carItem.getCarId()+"\t"+" 商品名称:"+good.getName()+"\t"+" 购买数目:"+carItem.getNum());
                                total+=carItem.getNum()*good.getPrice();
                            }
                        }
                    }
                    System.out.println("总价:"+total);
                    break;
                case "2":
                    System.out.print("请输入购物车条目编号:");
                    int carId = input.nextInt();
                    input.nextLine();
                    boolean flag=true;
                    CarItem carItemTemp=null;
                    for(CarItem carItem : car){
                        if(carItem.getCarId()==carId){
                            flag=false;
                            carItemTemp=carItem;
                        }
                    }
                    if(flag){
                        System.out.println("该条目不存在!");
                    }else{
                        car.remove(carItemTemp);
                        for(Good good:goods){
                            if(good.getGid()==carItemTemp.getGid()){
                                good.setNums(good.getNums()+carItemTemp.getNum());
                            }
                        }
                        System.out.println("删除成功！");
                    }
                    break;
                case "3":
                    if(car.size()>0){
                        double total2=0;
                        for(CarItem carItem : car){
                            for(Good good :goods){
                                if(carItem.getGid()==good.getGid()){
                                    System.out.println("购物车条目编号:"+"\t"+carItem.getCarId()+"\t"+"商品名称:"+good.getName()+"\t"+"购买数目:"+carItem.getNum());
                                    total2+=carItem.getNum()*good.getPrice();
                                }
                            }
                        }
                        System.out.println("总计:"+total2);
                        if(loginUser.getBalance()>=total2){
                            Order order = new Order(orderId,total2);
                            orders.add(order);
                            // 添加订单条目
                            for(CarItem carItem:car){
                                for(Good good :goods){
                                    if(carItem.getGid()==good.getGid()){
                                        OrderItem orderItem = new OrderItem(orderItemId++,carItem.getNum(),good.getGid(),orderId);
                                        orderItems.add(orderItem);
                                        good.setNums(good.getNums()-carItem.getNum());
                                    }
                                }
                            }
                            orderId++;
                            // 清空购物车
                            car= new ArrayList<CarItem>();
                            System.out.println("购物完成！本次消费"+total2+"元");
                            // 余额结算
                            loginUser.setBalance(loginUser.getBalance()-total2);
                            // 条目编号重新开始
                            orderItemId=0;
                        }else{
                            System.out.println("余额不足!请先充值!");
                        }
                    }else{
                        System.out.println("当前没有可结算的商品!赶快去添加商品到购物车吧!");
                    }
                    break;
                case "4":
                    flagTotal=true;
                    break;
                default:
                    System.out.println("输入有误!请重新输入!");
            }
            if(flagTotal){
                break;
            }

        }
    }

    /**
     * 浏览商品
     */
    private static void showGoods() {
        while(true){
            for (Good good : goods){
                System.out.println(good);
            }
            System.out.println("1:加入购物车 2:返回首页");
            System.out.print("请选择:");
            String choose=input.nextLine();
            boolean flag=false;
            switch(choose){
                case "1":
                   try{
                       System.out.print("请输入要购买的商品编号:");
                       int gid =Integer.parseInt(input.nextLine());
                       System.out.print("请输入购买数目:");
                       int number =Integer.parseInt(input.nextLine());
                       boolean flag2=true;
                       for(Good good :goods){
                          if(good.getGid()==gid){
                              flag2=false;
                              if(number<=good.getNums()){
                                  CarItem carItem = new CarItem(carItemId++,gid,number);
                                  car.add(carItem);
                                  good.setNums(good.getNums()-number);
                                  System.out.println("已添加购物车!");
                              }else{
                                  System.out.println("无法添加，已超出商品最大库存!");
                              }
                              break;
                          }
                      }
                       if(flag2){
                           System.out.println("输入的商品编号不存在!");
                       }
                   }catch (Exception e){
                       System.out.println("输入有误!请重新输入!");
                   }
                    break;
                case "2":
                    flag=true;
                    break;
                default:
                    System.out.println("输入错误!请重新输入!");
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 首页菜单
     */
    private static void showHomeMenu() {
        System.out.println("1:浏览商品");
        System.out.println("2:购物车");
        System.out.println("3:购物记录");
        System.out.println("4:用户余额查询及充值");
        System.out.println("5:离开商城");
    }

    /**
     * 加载用户数据
     */
    private static  void loadUser() {
        File file = new File("src/main/resourse/Users.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                String[] infos=line.split(" " );
                User user = new User(infos[0],infos[1],Double.parseDouble(infos[2]));
                users.add(user);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载商品
     */
    private static  void loadGoods() {
        File file = new File("src/main/resourse/goods.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                String[] infos=line.split(" " );
                Good good = new Good(Integer.parseInt(infos[0]),infos[1],Float.parseFloat(infos[2]),Integer.parseInt(infos[3]),infos[4]);
                goods.add(good);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
