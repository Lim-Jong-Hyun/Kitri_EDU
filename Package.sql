CREATE OR REPLACE 
PACKAGE PRODUCT_PACK AS 
procedure add_product(n product.name%type, 
                                    price product.price%type,
                                    quantity product.quantity%type);
procedure get_by_num(n product.num%type);
procedure get_by_name(n product.name%type);
procedure edit_price(n product.num%type,
                                p number);
procedure edit_quantity(n product.num%type,
                                    qua number);
procedure del_product(n product.num%type);
procedure print_all;
procedure out_product(n in product.num%type, qua in number, flag out boolean);
procedure out_product2(n in product.num%type);
END PRODUCT_PACK;
=================================================
CREATE OR REPLACE
PACKAGE BODY PRODUCT_PACK AS

  procedure add_product(n product.name%type, 
                                    price product.price%type,
                                    quantity product.quantity%type) AS
  BEGIN
    insert into product values(seq_product.nextval, n, price, 
    quantity);
  END add_product;

  procedure get_by_num(n product.num%type) AS
    vprod product%rowtype;
    vcnt number:=0;
  BEGIN
    select count(*) into vcnt from product where num=n;
    DBMS_OUTPUT.PUT_LINE(vcnt);
    if(vcnt=0) then
        DBMS_OUTPUT.PUT_LINE('없는 상품번호');
    else
        select * into vprod from product where num=n;
        DBMS_OUTPUT.PUT_LINE(vprod.num||' / '||vprod.name||' / '||
        vprod.price||' / '||vprod.quantity);
    end if;
  END get_by_num;

  procedure get_by_name(n product.name%type) AS
    cursor c is select * from product where name=n;
    vprod product%rowtype;
    vcnt number:=0;
  BEGIN
    open c;
 --   if(c%rowcount=0) then
 --       DBMS_OUTPUT.PUT_LINE('없는 제품명');
 --   else
        loop
            fetch c into vprod;
            exit when c%NOTFOUND;
            DBMS_OUTPUT.PUT_LINE(vprod.num||' / '||vprod.name||' / '||
            vprod.price||' / '||vprod.quantity);
        end loop;
 --   end if;
    close c;
  END get_by_name;

  procedure edit_price(n product.num%type,
                                p number) AS
  BEGIN
    update product set price=p where num=n;
  END edit_price;

  procedure edit_quantity(n product.num%type,
                                    qua number) AS
  BEGIN
    update product set quantity=quantity+qua where num=n;
  END edit_quantity;

  procedure del_product(n product.num%type) AS
    onum order_pack.onums;
    vcnt number;
    q number;
    flag boolean:=false;
BEGIN
    select count(*) into vcnt from product where num=n;
    if(vcnt>0) then
        order_pack.pay_no(n, onum, vcnt);
        for j in 1..vcnt loop
            DBMS_OUTPUT.PUT_LINE(onum(j)||' 주문이 상품이 품절되어 취소됩니다');
        end loop;
    
        order_pack.pay_yes_out_no(n, onum, vcnt);
        for j in 1..vcnt loop
            order_pack.get_qua(onum(j), q);
            out_product(n, q, flag);
            if(flag=false) then
                update my_order set out='y' where num=onum(j);
            end if;
            exit when (flag=true);
        end loop;
        if(flag) then
            DBMS_OUTPUT.PUT_LINE('출고처리 안된 주문이 있어 삭제가 취소됨');
        else
            DBMS_OUTPUT.PUT_LINE(n||' 제품이 삭제됨');
            --delete product where num=num;
        end if;
    else
        DBMS_OUTPUT.PUT_LINE('없는 번호');
    end if;
  END del_product;

  procedure print_all AS
    cursor c is select * from product;
    vprod product%rowtype;
    vcnt number:=0;
  BEGIN
    open c;
    if(c%rowcount=0) then
        DBMS_OUTPUT.PUT_LINE('제품목록이 없다');
    else
        loop
            fetch c into vprod;
            exit when c%NOTFOUND;
            DBMS_OUTPUT.PUT_LINE(vprod.num||' / '||vprod.name||' / '||
            vprod.price||' / '||vprod.quantity);
        end loop;
    end if;
    close c;
  END print_all;

  procedure out_product(n in product.num%type, qua in number, flag out boolean) AS
    q number;
  BEGIN
    select quantity into q from product where num=n;
    if(q<qua) then
        DBMS_OUTPUT.PUT_LINE('물량부족으로 출고처리 취소');
        flag:=true;
    else
        update product set quantity=quantity-qua where num=n;
        flag:=false;
    end if;
  END out_product;
  
procedure out_product2(n in product.num%type)as
    onum order_pack.onums;
    vcnt number;
    q number;
    flag boolean:=false;
begin
    order_pack.pay_yes_out_no(n, onum, vcnt);
    if(vcnt<=0) then
        DBMS_OUTPUT.PUT_LINE('출고할 내역이 없음');
    else
        for j in 1..vcnt loop
            order_pack.get_qua(onum(j), q);
            out_product(n, q, flag);
            if(flag=false) then
                update my_order set out='y' where num=onum(j);
            end if;
            exit when (flag=true);
        end loop;
        if(flag) then
            DBMS_OUTPUT.PUT_LINE('출고가 중단됨');
        end if;
    end if;
END out_product2;

END PRODUCT_PACK;

=============================================
CREATE OR REPLACE 
PACKAGE ORDER_PACK 
AS 
type onums is table of my_order.num%type 
index by binary_integer;
  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
procedure pay_no(pnum in product.num%type, nums out onums, 
cnt out number);
procedure pay_yes_out_no(pnum in product.num%type, 
nums out onums, cnt out number);
procedure get_qua(n in my_order.num%type, 
q out my_order.order_num%type);
END ORDER_PACK;
===========================================
CREATE OR REPLACE
PACKAGE BODY ORDER_PACK AS

  procedure pay_no(pnum in product.num%type, nums out onums, 
  cnt out number) AS
     i binary_integer:=0;
  BEGIN
  for k in(select num from my_order where p_num=pnum and pay='n') loop
        i:=i+1;
        nums(i):=k.num;
    end loop;
    cnt:=i;
  END pay_no;

  procedure pay_yes_out_no(pnum in product.num%type, nums out onums, 
  cnt out number) AS
     i binary_integer:=0;
  BEGIN
  for k in(select num from my_order where p_num=pnum and pay='y' and out='n') loop
        i:=i+1;
        nums(i):=k.num;
    end loop;
    cnt:=i;
  END pay_yes_out_no;
  
procedure get_qua(n in my_order.num%type, 
q out my_order.order_num%type) 
as
begin
select order_num into q from my_order where num=n;
end get_qua;


END ORDER_PACK;