// /*
//  * Auto-generated file. Created by MyBatis Generator
//  */
// package com.example.demo.database

// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.address
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.characteristics
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.dependentsCount
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.gender
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.hireDate
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.id
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.image
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.mailAddress
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.name
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.salary
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.telephone
// import com.example.demo.database.EmployeesDynamicSqlSupport.Employees.zipCode
// import com.example.demo.database.EmployeesRecord
// import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
// import org.mybatis.dynamic.sql.util.kotlin.*
// import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

// fun EmployeesMapper.count(completer: CountCompleter) =
//     countFrom(this::count, Employees, completer)

// fun EmployeesMapper.delete(completer: DeleteCompleter) =
//     deleteFrom(this::delete, Employees, completer)

// fun EmployeesMapper.deleteByPrimaryKey(id_: Int) =
//     delete {
//         where(id, isEqualTo(id_))
//     }

// fun EmployeesMapper.insert(record: EmployeesRecord) =
//     insert(this::insert, record, Employees) {
//         map(id).toProperty("id")
//         map(name).toProperty("name")
//         map(image).toProperty("image")
//         map(gender).toProperty("gender")
//         map(hireDate).toProperty("hireDate")
//         map(mailAddress).toProperty("mailAddress")
//         map(zipCode).toProperty("zipCode")
//         map(address).toProperty("address")
//         map(telephone).toProperty("telephone")
//         map(salary).toProperty("salary")
//         map(characteristics).toProperty("characteristics")
//         map(dependentsCount).toProperty("dependentsCount")
//     }

// fun EmployeesMapper.insertMultiple(records: Collection<EmployeesRecord>) =
//     insertMultiple(this::insertMultiple, records, Employees) {
//         map(id).toProperty("id")
//         map(name).toProperty("name")
//         map(image).toProperty("image")
//         map(gender).toProperty("gender")
//         map(hireDate).toProperty("hireDate")
//         map(mailAddress).toProperty("mailAddress")
//         map(zipCode).toProperty("zipCode")
//         map(address).toProperty("address")
//         map(telephone).toProperty("telephone")
//         map(salary).toProperty("salary")
//         map(characteristics).toProperty("characteristics")
//         map(dependentsCount).toProperty("dependentsCount")
//     }

// fun EmployeesMapper.insertMultiple(vararg records: EmployeesRecord) =
//     insertMultiple(records.toList())

// fun EmployeesMapper.insertSelective(record: EmployeesRecord) =
//     insert(this::insert, record, Employees) {
//         map(id).toPropertyWhenPresent("id", record::id)
//         map(name).toPropertyWhenPresent("name", record::name)
//         map(image).toPropertyWhenPresent("image", record::image)
//         map(gender).toPropertyWhenPresent("gender", record::gender)
//         map(hireDate).toPropertyWhenPresent("hireDate", record::hireDate)
//         map(mailAddress).toPropertyWhenPresent("mailAddress", record::mailAddress)
//         map(zipCode).toPropertyWhenPresent("zipCode", record::zipCode)
//         map(address).toPropertyWhenPresent("address", record::address)
//         map(telephone).toPropertyWhenPresent("telephone", record::telephone)
//         map(salary).toPropertyWhenPresent("salary", record::salary)
//         map(characteristics).toPropertyWhenPresent("characteristics", record::characteristics)
//         map(dependentsCount).toPropertyWhenPresent("dependentsCount", record::dependentsCount)
//     }

// private val columnList = listOf(id, name, image, gender, hireDate, mailAddress, zipCode, address, telephone, salary, characteristics, dependentsCount)

// fun EmployeesMapper.selectOne(completer: SelectCompleter) =
//     selectOne(this::selectOne, columnList, Employees, completer)

// fun EmployeesMapper.select(completer: SelectCompleter) =
//     selectList(this::selectMany, columnList, Employees, completer)

// fun EmployeesMapper.selectDistinct(completer: SelectCompleter) =
//     selectDistinct(this::selectMany, columnList, Employees, completer)

// fun EmployeesMapper.selectByPrimaryKey(id_: Int) =
//     selectOne {
//         where(id, isEqualTo(id_))
//     }

// fun EmployeesMapper.update(completer: UpdateCompleter) =
//     update(this::update, Employees, completer)

// fun KotlinUpdateBuilder.updateAllColumns(record: EmployeesRecord) =
//     apply {
//         set(id).equalTo(record::id)
//         set(name).equalTo(record::name)
//         set(image).equalTo(record::image)
//         set(gender).equalTo(record::gender)
//         set(hireDate).equalTo(record::hireDate)
//         set(mailAddress).equalTo(record::mailAddress)
//         set(zipCode).equalTo(record::zipCode)
//         set(address).equalTo(record::address)
//         set(telephone).equalTo(record::telephone)
//         set(salary).equalTo(record::salary)
//         set(characteristics).equalTo(record::characteristics)
//         set(dependentsCount).equalTo(record::dependentsCount)
//     }

// fun KotlinUpdateBuilder.updateSelectiveColumns(record: EmployeesRecord) =
//     apply {
//         set(id).equalToWhenPresent(record::id)
//         set(name).equalToWhenPresent(record::name)
//         set(image).equalToWhenPresent(record::image)
//         set(gender).equalToWhenPresent(record::gender)
//         set(hireDate).equalToWhenPresent(record::hireDate)
//         set(mailAddress).equalToWhenPresent(record::mailAddress)
//         set(zipCode).equalToWhenPresent(record::zipCode)
//         set(address).equalToWhenPresent(record::address)
//         set(telephone).equalToWhenPresent(record::telephone)
//         set(salary).equalToWhenPresent(record::salary)
//         set(characteristics).equalToWhenPresent(record::characteristics)
//         set(dependentsCount).equalToWhenPresent(record::dependentsCount)
//     }

// fun EmployeesMapper.updateByPrimaryKey(record: EmployeesRecord) =
//     update {
//         set(name).equalTo(record::name)
//         set(image).equalTo(record::image)
//         set(gender).equalTo(record::gender)
//         set(hireDate).equalTo(record::hireDate)
//         set(mailAddress).equalTo(record::mailAddress)
//         set(zipCode).equalTo(record::zipCode)
//         set(address).equalTo(record::address)
//         set(telephone).equalTo(record::telephone)
//         set(salary).equalTo(record::salary)
//         set(characteristics).equalTo(record::characteristics)
//         set(dependentsCount).equalTo(record::dependentsCount)
//         where(id, isEqualTo(record::id))
//     }

// // fun EmployeesMapper.updateByPrimaryKeySelective(record: EmployeesRecord) =
// //     update {
// //         set(name).equalToWhenPresent(record::name)
// //         set(image).equalToWhenPresent(record::image)
// //         set(gender).equalToWhenPresent(record::gender)
// //         set(hireDate).equalToWhenPresent(record::hireDate)
// //         set(mailAddress).equalToWhenPresent(record::mailAddress)
// //         set(zipCode).equalToWhenPresent(record::zipCode)
// //         set(address).equalToWhenPresent(record::address)
// //         set(telephone).equalToWhenPresent(record::telephone)
// //         set(salary).equalToWhenPresent(record::salary)
// //         set(characteristics).equalToWhenPresent(record::characteristics)
// //         set(dependentsCount).equalToWhenPresent(record::dependentsCount)
// //         where(id, isEqualTo(record::id))
// //     }
