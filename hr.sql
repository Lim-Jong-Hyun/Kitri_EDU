
--1 ��������--
describe departments;

select * from departments;

select CONCAT(first_name, last_name) as name , job_id, hire_date as STARTDATE
from employees;

describe employees;

select employee_id, last_name, job_id, hire_date as STARTDATE
from employees;

select distinct job_id
from employees;

select employee_id as EMP#, last_name as Employee, job_id as Job , hire_date as "Hire Date"
from employees;

select last_name ||' , '|| job_id as "Employee and Title"
from employees;

select last_name ||' , '||first_name ||' , '||last_name ||' , '||email ||' , '||phone_number ||' , '||
hire_date ||' , '||job_id ||' , '||salary ||' , '||commission_pct ||' , '||manager_id ||' , '|| department_id
as "THE_OUTPUT"
from employees;


select last_name, salary
from employees
where salary>12000;

select last_name, department_id
from employees
where employee_id=176;

select last_name, salary
from employees
where salary<5000 OR salary>12000;

select last_name, job_id, hire_date
from employees
where hire_date between '05/02/20' and '05/05/01';

select last_name, department_id
from employees
where department_id = 20 OR department_id = 50
order by last_name;

select last_name as Employee, salary as "Monthly Salary"
from employees
where (salary between 5000 and 12000) and (department_id =20 OR department_id=50);

select last_name, hire_date
from employees
where hire_date LIKE '04%';

select last_name, job_id
from employees
where manager_id is null;

select last_name, salary, commission_pct
from employees
where commission_pct is not null
order by commission_pct desc;

select last_name
from employees
where last_name like '__a%';

select last_name
from employees
where last_name like '%a%' OR last_name like '%e%';

select last_name, job_id, salary
from employees
where (job_id = 'ST_CLERK' OR job_id='SA_REP') AND (salary not in (2500, 3500, 7000));

select last_name as Employee, salary as "Monthly Salary", commission_pct as "COMMISSION_PCT"
from employees
where commission_pct=0.2;

create table job_grades(
gra varchar2(2) primary key,
lowest_sal number(6) not null,
highest_sal number(6) not null);

insert into job_grades values('A',1000,2999);
insert into job_grades values('B',3000,5999);
insert into job_grades values('C',6000,9999);
insert into job_grades values('D',10000,14999);
insert into job_grades values('E',15000,24999);
insert into job_grades values('F',25000,40000);

select * from job_grades;

select employee_id, last_name,salary,gra
from employees, job_grades
where salary between lowest_sal and highest_sal;

select last_name, department_name dept_name
from employees, departments;

select e.employee_id, e.last_name, e.department_id, d.department_id, d.location_id
from employees e, departments d
where e.department_id = d.department_id;


-------3 �ܿ�
select sysdate as "Date"
from dual;

select employee_id, last_name, salary, salary *1.15 as "New Salary"
from employees;

select employee_id, last_name, salary, salary *1.15 as "New Salary", salary*1.15-salary as Increase
from employees;

select initcap(last_name) as "Name", length(last_name) as "Length"
from employees
where last_name like 'J%' OR last_name like 'A%' OR last_name like 'M%';

select last_name, round((sysdate-hire_date)/30, 0) as "MONTHS_WORKED"
from employees;

select last_name ||' earns '|| salary ||' monthly but wants '|| 3*salary as "Dream Salary"
from employees;

select last_name, lpad(salary, 15, '$')
from employees;

select last_name, hire_date, TO_CHAR(NEXT_DAY(ADD_MONTHS(hire_date, 6),'������'), 'day, fmDdspth "of" Month, YYYY' )as Review
from employees;

select last_name, hire_date, TO_CHAR(hire_date, 'day') as "DAY"
from employees
order by TO_CHAR(hire_date-1, 'D') asc;

select last_name, nvl(TO_CHAR(commission_pct),'No Commission') as "COMM"
from employees;

select last_name || lpad(' ',salary/1000,'*')
as "EMPLOYEE_AND_THEIR_SALARIES"
from employees
order by salary desc;

select job_id, DECODE(job_id, 'AD_PRES', 'A', 'ST_MAN', 'B', 'IT_PROG', 'C', 'SA_REP', 'D', 'ST_CLERK', 'E' , '0')G
from employees;

select job_id, CASE job_id when 'AD_PRES' then 'A'
                     when 'ST_MAN' then  'B'
                     when 'IT_PROG' then  'C'
                     when 'SA_REP' then  'D'
                     when 'ST_CLERK' then 'E'
                     else  '0' END "G"
from employees;


select last_name, department_name
from employees
cross join departments;

select department_id, department_name, location_id, city
from departments
natural join locations;

select e.employee_id, e.last_name, d.location_id
from employees e
join departments d
using (department_id);

select e.last_name, e.department_id, d.department_name
from employees e
left outer join departments d
on (e.department_id = d.department_id);

--4��--
select e.last_name, e.department_id, d.department_name
from employees e, departments d
where e.department_id =  d.department_id;

select job_id, location_id
from employees e, departments d
where e.department_id =  d.department_id and e.department_id = 80;

select e.last_name, d.department_name, l.location_id, l.city
from employees e, locations l, departments d
where e.department_id = d.department_id and d.location_id = l.location_id
and commission_pct is not null;

select last_name, department_name
from employees, departments
where last_name like '%a%' and
employees.department_id = departments.department_id;

select e.last_name, e.job_id, d.department_id, d.department_name
from employees e, departments d, locations l
where e.department_id = d.department_id and d.location_id = l.location_id and
l.city = 'Toronto';

select e1.last_name as "Employee", e1.employee_id as "EMP#", e2.last_name as "Manager", e2.employee_id as "Mgr#"
from employees e1, employees e2
where e1.manager_id = e2.employee_id;

select e1.last_name as "Employee", e1.employee_id as "EMP#", e2.last_name as "Manager", e2.employee_id as "Mgr#"
from employees e1, employees e2
where e1.manager_id = e2.employee_id(+)
order by e1.employee_id asc;


select e1.department_id, e1.last_name "EMPLOYEE", e2.last_name "COLLEAGUE"
from employees e1, employees e2
where e1.department_id = e2.department_id
and e1.last_name <> e2.last_name
order by e1.last_name desc;

desc job_grades;

select last_name, job_id, department_name, e.salary,gra
from employees e join departments d
on e.department_id = d.department_id
join job_grades
on salary between lowest_sal and highest_sal;

select e2.last_name, e2.hire_date
from employees e1, employees e2
where e1.last_name = 'Davies' and e1.hire_date - e2.hire_date > 0;

select e1.last_name, e1.hire_date, e2.last_name, e2.hire_date
from employees e1, employees e2
where e1.manager_id = e2.employee_id
and e1.hire_date - e2.hire_date <0;

select max(salary) as Maximum, round(min(salary),0) as Mininmum, sum(salary) as "Sum", round(avg(salary),0) as "Average"
from employees;

select job_id, max(salary) as Maximum, round(min(salary),0) as Mininmum, sum(salary) as "Sum", round(avg(salary),0) as "Average"
from employees
group by job_id;

select job_id, count(*)
from employees
group by job_id;

select count(distinct manager_id)
from employees
where manager_id is not null;

select max(salary)-min(salary) as "DIFFERENCE"
from employees;

select manager_id, min(salary)
from employees
group by manager_id
having min(salary)>6000 and manager_id is not null
order by min(salary) desc;

select d.department_name, d.location_id, sum(e.employee_id)as "Number of People", round(avg(e.salary), 2) as Salary
from departments d, employees e
where d.department_id = e.department_id
group by d.department_name, d.location_id, e.department_id;

select sum(employee_id) as TOTAL,
count (case when to_char(hire_date, 'yy')=05 then 1 END) AS "2005",
count (case when to_char(hire_date, 'yy')=06 then 1 END) AS "2006",
count (case when to_char(hire_date, 'yy')=07 then 1 END) AS "2007",
count (case when to_char(hire_date, 'yy')=08 then 1 END) AS "2008"
from employees;

select e.job_id as JOB,
 nvl(case when e.department_id =20 then sum(salary) END,0) AS "Dept 20",
 nvl(case when e.department_id =50 then sum(salary) END,0 ) AS "Dept 50",
 nvl(case when e.department_id =80 then sum(salary) END,0 ) AS "Dept 80",
 nvl(case when e.department_id =90 then sum(salary) END,0 ) AS "Dept 90",
 sum(e.salary) as Total
from departments d, employees e
where d.department_id = e.department_id
group by  e.job_id, e.department_id;

select last_name, hire_date
from employees
where department_id = (select department_id
                       from employees
                       where last_name='Zlotkey');

select employee_id, last_name, salary
from employees
where salary > (select avg(salary)
                from employees)
order by salary asc;

select employee_id, last_name
from employees
where last_name  = any(select last_name
                    from employees
                    where last_name like '%u%');

select e.last_name, d.department_id, e.job_id
from employees e, departments d
where e.department_id = d.department_id and d.location_id = 1700;

select last_name, salary
from employees
where manager_id = (select employee_id
                    from employees
                    where last_name ='King' and first_name = 'Steven');

select d.department_id, e.last_name, e.job_id
from employees e, departments d
where e.department_id = d.department_id and
d.department_name = 'Executive';


select employee_id, last_name, salary
from employees
where last_name  = any(select last_name
                    from employees
                    where last_name like '%u%')
                    and
    salary > (select avg(salary)
              from employees);


create table dept
            (deptno number(2),
             dname varchar2(14),
             loc varchar2(13));

desc dept;

select table_name
from user_tables;

select distinct object_type
from user_objects;

select *
from user_catalog;

create table MY_EMPLOYEE (
ID number(4) not null PRIMARY KEY,
LAST_NAME VARCHAR2(25),
FIRST_NAME VARCHAR2(25),
USER_ID VARCHAR2(8),
SALARY NUMBER(9,2));

desc my_employee;

alter table my_employee
add constraint pr primary key(id);

insert into my_employee values(1,'Patel','Ralph','rpatel',895);

select * from my_employee;

insert into my_employee(id,last_name,first_name,user_id,salary) values (2,'Dancs','Betty','bdancs',860);

select * from my_employee;

insert into my_employee values(3,'Bin','Ben','bbin',1100);
insert into my_employee values(4,'Newman','Chad','cnewman',750);

select * from my_employee;
commit;

update my_employee set last_name = 'Drexler' where id=3;

select * from my_employee;

update my_employee set salary = 1000 where salary<900;

select * from my_employee;

delete from my_employee where id = '5';

select * from my_employee;
commit;

insert into my_employee values(5,'Ropeburn','Audrey','aropebur',1550);
select * from my_employee;

savepoint sa;
delete from my_employee;
select * from my_employee;


drop table dept;
create table DEPT(
id number(7),
name varchar2(25)
);

select * from dept;

insert into dept(select department_id, department_name from departments);

create table emp (
id number(7),
last_name varchar2(25),
first_name varchar2(25),
dept_id number(7));

alter table emp
modify last_name varchar2(50);

desc emp;

select table_name
from user_tables;

create table employees2
as select employee_id as "ID",first_name,last_name,salary,department_id as "DEPT_ID"
from employees;

select * from employees2;
drop table emp;

rename employees2 to emp;

desc emp;

alter table emp
drop column first_name;

desc emp;

create table sales_reps(
id number(6,0) primary key,
name varchar2(25),
salary number(8, 2),
comm number(2,2)
);

insert into sales_reps(id, name) values(1000, 'aaa');
insert into sales_reps values(1001, 'bbb', 3000, .2);
select * from sales_reps;


insert into sales_reps
select employee_id, last_name, salary, commission_pct
from employees
where job_id like '%REP%';

update sales_reps
set salary=5000, comm=.3
where id=1000;

commit;

delete from sales_reps
where id=1000;
rollback;

create table member(
id varchar2(20) primary key,
pwd varchar2(20) not null,
name varchar2(20) not null,
email varchar2(40)
);

create table board(
num number primary key,
writer varchar2(20) references member(id),
w_date date,
title varchar2(20),
memo varchar2(50)
);

create sequence seq_board;

insert into member values('aaa', '111', 'namea', 'emaila');
insert into member values('bbb', '222', 'nameb', 'emailb');
insert into member values('ccc', '333', 'namec', 'emailc');

select pwd from member where id='aaa';
update member set pwd='444', name='xxx', email='yyy'
where id='bbb';
select * from member where id='bbb';
delete member where id='bbb';
select * from member;

select seq_board.nextval from dual;
select seq_board.currval from dual;

insert into board values(seq_board.nextval, 'aaa', sysdate, 'title1',
'content1');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title1',
'content1');
update board set title='title2', memo='content2'
where num=5;
insert into board values(seq_board.nextval, 'aaa', sysdate, 'title3',
'content3');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title4',
'content4');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title4',
'content4');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title4',
'content4');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title4',
'content4');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title4',
'content4');
insert into board values(seq_board.nextval, 'ccc', sysdate, 'title4',
'content4');
select * from board where writer='aaa';
select * from board;

define page=3;
define n =5;

select *
from (select rownum r, num, writer,w_date,title,memo from board)
where r between &n * (&page-1) and &n *(&page);

define val1 =100;
define val2 = "aaa";
define;

select &val1, '&val2' from dual;

@aa 5 "LLLIN" "JONJF" 100

select * from my_employee;

disconn;
conn system/123456;

create user lim
identified by 123456;

show user;
create user user01 identified by 1234;
grant create session to user01;

set serveroutput on

begin
DBMS_OUTPUT.PUT_LINE('HELLO PL/SQL');
end;
declare
    vempno number(4);
    vename varchar2(10);
    vempno2 employees.employee_id%type;
    vdeptname departments.department_name%type;
    vjob employees.job_id%type;
begin
    vempno :=1234;
    vename :='aaaa';
    select employee_id ,job_id ,last_name, department_name
    into vempno, vjob, vename, vdeptname
    from employees, departments
    where employees.department_id = departments.department_id and employee_id=150;
    DBMS_OUTPUT.PUT_LINE('emp no / emp job / emp name / emp dept');
    DBMS_OUTPUT.PUT_LINE(vempno || ' / '|| vjob ||' / ' || vename || ' / ' || vdeptname );
end;


declare
    vscore number(3);
begin
    vscore :=45;
    if (vscore >=60) then DBMS_OUTPUT.PUT_LINE('A');
    elsif (vscore>=50 and vscore<60) then DBMS_OUTPUT.PUT_LINE('B');
    elsif (vscore>=40 and vscore<50) then DBMS_OUTPUT.PUT_LINE('C');
    elsif (vscore>=30 and vscore<40) then DBMS_OUTPUT.PUT_LINE('D');
    else DBMS_OUTPUT.PUT_LINE('F');
    end if;
end;


declare
    vsalary employees.salary%type;
    vcomm employees.commission_pct%type;
    vann number(7,2);
    venum employees.employee_id%type;
begin
    venum:=145;
    select salary, commission_pct
    into vsalary, vcomm
    from employees
    where employee_id = venum;
    if(vcomm is null) then vann := vsalary*12;
    else vann := vsalary * 12 + (vsalary * vcomm * 12);
    end if;
    DBMS_OUTPUT.PUT_LINE('������ ' || vann||' �Դϴ�');
end;


declare
    x number:=1;
    ssum number:=0;
begin
    loop
        ssum := sum(ssum+x);
        x:=x+1;
        if(x>100) then exit;
        end if;
    end loop;
    DBMS_OUTPUT.PUT_LINE(ssum);
end;


declare
    vemp employees%rowtype;
    vcount number;
    vname varchar2(20);
begin
        select *
        into vemp
        from employees
        where employee_id = &vcount;
        DBMS_OUTPUT.PUT_LINE('���� ��ȣ : ' ||vcount||' / ' || '���� �̸� : ' ||vemp.last_name||
        ' / ' || '���� ���� : ' ||vemp.job_id||' / ' || '���� �μ� : ' ||vemp.department_id);
        vcount := vcount+1;
end;


declare
    x number:=1;
    y number;
begin
        y :=&y;
        for x in 1..9 loop
            DBMS_OUTPUT.PUT_LINE(y || ' * ' || x || ' = '|| x*y);
        end loop;
end;

declare
    dept departments%rowtype;
    x number:=10;
begin

    loop
        select *
        into dept
        from departments
        where department_id = x;
        DBMS_OUTPUT.PUT_LINE( dept.department_id || ' / '|| dept.department_name ||' / '|| dept.manager_id ||' / '|| dept.location_id );
        x:=x+10;
        exit when x>270;
    end loop;
end;


declare
    x number:=1;
    y number:=1;
begin
    while x<10 loop
        x:=x+1;
        while y<10 loop
            DBMS_OUTPUT.PUT_LINE(x*y);
            y:=y+1;
        end loop;
        y:=0;
    end loop;
end;


declare
    type ename_arr is table of employees.last_name%type
    index by binary_integer;
    type job_arr is table of employees.job_id%type
    index by binary_integer;
    names ename_arr;
    jobs job_arr;
    i binary_integer:=0;

begin
    for k in (select last_name, job_id from employees) loop
        i:=i+1;
        names(i):=k.last_name;
        jobs(i):=k.job_id;
    end loop;

    for j in 1..i loop
        DBMS_OUTPUT.PUT_LINE(names(j) || ' / ' || jobs(j));
    end loop;
end;

declare
    type emp_record is record(
    vnum employees.employee_id%type,
    vname employees.last_name%type,
    vsalary employees.salary%type,
    vjob employees.job_id%type);

    vemp_rec emp_record;
begin
    select employee_id, last_name, salary, job_id
    into vemp_rec
    from employees
    where employee_id=100;
    DBMS_OUTPUT.PUT_LINE(vemp_rec.vnum || ' / ' || vemp_rec.vname || ' / ' || vemp_rec.vjob);
end;


select * from factory;
select * from market;


declare
    vnum number;
    vcount number:=0;
    type vmarket_arr is table of market%rowtype index by binary_integer;
    vmarket vmarket_arr;
    i binary_integer :=0;
begin
    vnum := &vnum;
    for k in (select * from market) loop
        i := i+1;
        vmarket(i):=k;
    end loop;
    for j in 1..i loop
        if(vmarket(j).order_id = vnum) then  vcount:=j;
        end if;
    end loop;
    if(vcount != 0)
        then
        DBMS_OUTPUT.PUT_LINE('������ ������ ����');
        DBMS_OUTPUT.PUT_LINE(vmarket(vcount).order_id || ' / ' || vmarket(vcount).product_id || ' / ' || vmarket(vcount).order_amount||
        ' / ' || vmarket(vcount).order_price|| ' / ' || vmarket(vcount).pay|| ' / ' || vmarket(vcount).product_release );

        if( vmarket(vcount).pay ='yes' and  vmarket(vcount).product_release='no') then


    else DBMS_OUTPUT.PUT_LINE('������ ������ ����');
    end if;
end;


variable eid number;
variable ename varchar2(50);
variable job2 varchar2(50);
exec emp_pack.find_by_id(100,:eid, :ename, :job2);
print ename;
print eid;
print job2;

variable str varchar2(50);
exec :str:=emp_pack.test1(100);
print str;

create table emp_copy2 as select * from employees where 1=0;

desc emp_copy2;

select * from emp_copy2;

insert into emp_copy2 (select * from employees where department_id=80);
delete emp_copy2;

create or replace trigger emp_insert_after
after insert
on emp_copy2 for each row
begin
    DBMS_OUTPUT.PUT_LINE('���Ի����� �߰���');
end;

select * from emp_copy2;
select * from job_grades;

alter TABLE emp_copy2
add (sal_grade varchar2(2));

update emp_copy2  set sal_grade = (select gra
                                                from job_grades
                                                where emp_copy2.salary between job_grades.lowest_sal and  job_grades.highest_sal);


create or replace trigger emp_update_after
after update of salary
on emp_copy2
referencing new as new old as old
begin
    DBMS_OUTPUT.PUT_LINE('Update ������');
    update emp_copy2  set sal_grade = (select gra
                                                from job_grades
                                                where emp_copy2.salary between job_grades.lowest_sal and  job_grades.highest_sal);
end;

create or replace trigger emp_update_after
after update of salary
on emp_copy2 
referencing new as new
for each row
begin
    DBMS_OUTPUT.PUT_LINE('Update ������');
    update emp_copy2  set sal_grade = (select gra
                                                from emp_copy2 , job_grades
                                                where :new.salary between lowest_sal and  highest_sal)
                                                where employee_id = :new.employee_id;
end;

update emp_copy2 set salary = 1000 where employee_id =143;
