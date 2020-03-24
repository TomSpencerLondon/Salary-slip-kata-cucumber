package com.codurance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalarySlipGeneratorShould {

  private static final int ID = 123445;
  private static final String NAME = "John J Doe";

  private SalarySlipGenerator salarySlipGenerator;

  @BeforeEach
  void setUp() {
    salarySlipGenerator = new SalarySlipGenerator();
  }

  @Test
  public void generate_salary_slip_with_basic_employee_information() {
    Employee employee = anEmployee().withID(ID).withName(NAME).build();

    SalarySlip salarySlip = salarySlipGenerator.generateFor(employee);

    assertEquals(employee.ID(), salarySlip.employeeID());
  }

  @Test
  public void generate_slip_with_month_gross_salary() {
    Employee employee_1 = anEmployee().withAnnualGrossSalary(5000).build();
    Employee employee_2 = anEmployee().withAnnualGrossSalary(9060).build();

    SalarySlip salarySlip_1 = salarySlipGenerator.generateFor(employee_1);
    SalarySlip salarySlip_2 = salarySlipGenerator.generateFor(employee_2);

    assertEquals(salarySlip_1.monthlyGrossSalary(), BigDecimal.valueOf(416.67));
    assertEquals(755, salarySlip_2.monthlyGrossSalary().intValue());
  }
}
