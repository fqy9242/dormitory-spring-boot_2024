# 床位选择系统

- 大二劳动课作业，仿学校选床位系统。

采用vue3 + spring boot 前后端分离

数据库初始文件:**dormitory_backup_xxx.sql** 运行前请先创建名为**dormitory**的数据库

前端地址: [fqy9242/24-dormitory_vue3-js: 大二劳动课作业 宿管系统 (github.com)](https://github.com/fqy9242/24-dormitory_vue3-js)

后端地址: [fqy9242/dormitory-spring-boot_2024: 大二劳动课作业后端 宿管系统 (github.com)](https://github.com/fqy9242/dormitory-spring-boot_2024)

## 数据生成

注:数据库中信息均使python生成 不具备真实性 如有雷同 纯属巧合。

**生成的数据python代码如下**

```python
# 生成班级数据
def generate_class_to_dataBase(connection, n):
    fake = Faker('zh_CN')
    culture_type_list = ['', '本', '专升本', '中职升本']
    grade_list = ['21', '22', '23', '24']
    for i in tqdm(range(n), desc="正在生成班级数据"):
        selectMajor = "select college_name, major_name, major_code from major order by rand() limit 1;"
        cursor = connection.cursor()
        cursor.execute(selectMajor)
        result = cursor.fetchall()
        # 年级
        grade = grade_list[random.randint(0, len(grade_list) - 1)]
        # 学院名称
        college_name = result[0][0]
        # 专业名称
        major_name = result[0][1]
        # 专业代码
        major_code = result[0][2]
        # 几班
        class_range = random.randint(1, 2)
        # 培养类型
        culture_type = random.randint(1, len(culture_type_list) - 1)
        # 班级代码
        class_code = f"{grade}{major_code}{culture_type}0{class_range}"
        # 班级名称
        class_name = f"{major_name}({culture_type_list[culture_type]}){grade}0{class_range}"
        # 辅导员手机号
        teacher_phone_number = fake.phone_number()
        # 男生人数
        boy_amount = random.randint(10, 31)
        # 女生人数
        girl_amount = random.randint(10, 31)
        # 插入数据库
        insert_sql = ("insert into class(class_code, class_name, college_name, grade, boy_amount, girl_amount, "
                      "teacher_phone_number, create_time, update_time) values (%s, %s, %s, %s, %s, %s, %s, now(), "
                      "now());")
        cursor.execute(insert_sql,
                       (class_code, class_name, college_name, '20' + grade, boy_amount, girl_amount,
                        teacher_phone_number))
        cursor.close()
        connection.commit()
    # 关闭资源
    connection.close()
```

```python
# 生成宿舍数据
def generate_dormitory_to_database(connection):
    try:
        # 查询所有的楼栋
        select_building = "SELECT * FROM building;"
        with connection.cursor() as cursor:
            cursor.execute(select_building)
            building_list = cursor.fetchall()
            for building in tqdm(building_list, desc="正在生成并插入宿舍数据"):
                building_id = building[0]
                # building_name = building[1]
                floor_dormitory_amount = building[4]
                floor_amount = building[5]
                # 生成楼层
                for floor in range(1, floor_amount + 1):  # 包括floor_amount
                    # 生成宿舍
                    for room in range(1, floor_dormitory_amount + 1):  # 包括floor_dormitory_amount
                        dormitory_number = f"{floor}{room:02d}"  # 使用格式化字符串统一格式
                        bed_amount = "10"
                        insert_sql = ("INSERT INTO dormitory (dormitory_number, building_id, bed_amount, create_time, "
                                      "update_time) VALUES (%s, %s, %s, NOW(), NOW());")
                        cursor.execute(insert_sql, (dormitory_number, building_id, bed_amount))
            # 提交事务
            connection.commit()
    except Exception as e:
        print(f"An error occurred: {e}")
        connection.rollback()
```

```python
# 生成学生数据
def generate_student_to_database(connection):
    try:
        # 查询所有班级
        select_class = "SELECT * FROM class;"
        with connection.cursor() as cursor:
            cursor.execute(select_class)
            class_list = cursor.fetchall()
            for class_ in tqdm(class_list, desc="正在生成并插入学生数据"):
                class_name = class_[1]
                class_code = class_[2]
                student_amount = class_[5] + class_[6]
                for i in range(1, student_amount + 1):
                    # 判断学生是否已经存在
                    select_student = "SELECT id FROM student WHERE student_number = %s;"
                    cursor.execute(select_student, (class_code + str(i)))
                    result = cursor.fetchall()
                    if result:
                        continue
                    # 生成学生数据
                    student_name = fake.name()
                    student_number = f"{class_code}{i:02d}"
                    login_password = "123456"
                    gender = random.randint(1, 2)
                    emergency_contact_phone = fake.phone_number()
                    insert_sql = ("insert into student(name, student_number, login_password, gender, class_name, "
                                  "emergency_contact_phone, create_time, update_time) values (%s, %s, %s, %s, %s, %s, "
                                  "now(), now());")
                    cursor.execute(insert_sql, (student_name, student_number, login_password, gender, class_name,
                                                emergency_contact_phone))
        connection.commit()
    except Exception as e:
        print(f"An error occurred: {e}")
        connection.rollback()

```

