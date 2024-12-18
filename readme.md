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

