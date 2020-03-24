package acceptance.steps;

import com.codurance.Employee;
import com.codurance.SalarySlip;
import com.codurance.SalarySlipGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import static com.codurance.EmployeeBuilder.anEmployee;
import static org.junit.Assert.assertEquals;

public class GenerateSalarySlipFeature {
  private SalarySlip salarySlip;
  private Employee employee;

  private SalarySlipGenerator salarySlipGenerator;

  @Given("^an employee with an annual gross salary of £ (\\d+)$")
  public void creates_annual_gross_salary(BigDecimal annualGrossSalary) {
    employee = anEmployee().withAnnualGrossSalary(annualGrossSalary).build();
    salarySlipGenerator = new SalarySlipGenerator();
  }

  @When("^we generate the salary slip$")
  public void generates_salary_slip() {
    salarySlip = salarySlipGenerator.generateFor(employee);
  }

  @Then("^the salary slip should contain monthly gross salary of £ (\\d+\\.\\d+)$")
  public void generate_monthly_gross_salary(BigDecimal monthlyGrossSalary){
    assertEquals(salarySlip.monthlyGrossSalary(), twoDecimalCases(monthlyGrossSalary));
  }

  @Then("^national insurance contribution of £ (\\d+\\.\\d+)$")
  public void generate_national_insurance(BigDecimal nationalInsurance) {
    assertEquals(salarySlip.nationalInsurance(), twoDecimalCases(twoDecimalCases(nationalInsurance)));
  };

  @Then("^tax-free allowance of £ (\\d+\\.\\d+)$")
  public void generate_tax_free_allowance(BigDecimal taxFreeAllowance) {
    assertEquals(salarySlip.taxFreeAllowance(), twoDecimalCases(twoDecimalCases(taxFreeAllowance)));
  };

  @Then("^taxable income of £ (\\d+\\.\\d+)$")
  public void generate_taxable_income(BigDecimal taxableIncome) {
    assertEquals(salarySlip.taxableIncome(), twoDecimalCases(twoDecimalCases(taxableIncome)));
  };

  @Then("^tax payable of £ (\\d+\\.\\d+)$")
  public void generate_tax_payable(BigDecimal taxPayable) {
    assertEquals(salarySlip.taxPayable(), twoDecimalCases(twoDecimalCases(taxPayable)));
  };

  private BigDecimal twoDecimalCases(BigDecimal source) {
    return source.setScale(2, HALF_UP);
  }
}