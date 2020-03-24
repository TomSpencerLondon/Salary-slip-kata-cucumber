package com.codurance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SalarySlipShould {

  public static final Employee AN_EMPLOYEE = anEmployee().build();

  @ParameterizedTest
  @CsvSource({
          "6000, 0.00",
          "11000, 29.40",
          "12000, 39.40",
          "30000, 219.40"
  })
  public void inform_monthly_national_insurance(BigDecimal annualGrossSalary, BigDecimal nationalInsurance) {
    SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE,
            new NationalInsurance(annualGrossSalary),
            new TaxInfo(annualGrossSalary));

    assertEquals(nationalInsurance, salarySlip.nationalInsurance());
  }

  @ParameterizedTest
  @CsvSource({
          "6000, 500.00",
          "11000, 916.67",
          "12000, 916.67",
          "30000, 916.67"
  })
  public void inform_monthly_tax_free_allowance(BigDecimal annualGrossSalary, BigDecimal monthlyTaxFreeAllowance) {
    SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE,
            new NationalInsurance(annualGrossSalary),
            new TaxInfo(annualGrossSalary));

    assertEquals(monthlyTaxFreeAllowance, salarySlip.taxFreeAllowance());
  }

  @ParameterizedTest
  @CsvSource({
          "6000, 0.00",
          "11000, 0.00",
          "12000, 83.33",
          "30000, 1583.33"
  })
  public void inform_monthly_taxable_income(BigDecimal annualGrossSalary, BigDecimal monthlyTaxableIncome) {
    SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE,
            new NationalInsurance(annualGrossSalary),
            new TaxInfo(annualGrossSalary));

    assertEquals(monthlyTaxableIncome, salarySlip.taxableIncome());
  }
  @ParameterizedTest

  @CsvSource({
          "6000, 0.00",
          "11000, 0.00",
          "12000, 16.67",
          "30000, 316.67"
  })
  public void inform_monthly_tax_payable(BigDecimal annualGrossSalary, BigDecimal monthlyTaxableIncome) {
    SalarySlip salarySlip = new SalarySlip(AN_EMPLOYEE,
            new NationalInsurance(annualGrossSalary),
            new TaxInfo(annualGrossSalary));

    assertEquals(monthlyTaxableIncome, salarySlip.taxPayable());
  }
}
