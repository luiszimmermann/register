package register

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class BillingServiceSpec extends Specification {

    BillingService billingService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Billing(...).save(flush: true, failOnError: true)
        //new Billing(...).save(flush: true, failOnError: true)
        //Billing billing = new Billing(...).save(flush: true, failOnError: true)
        //new Billing(...).save(flush: true, failOnError: true)
        //new Billing(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //billing.id
    }

    void "test get"() {
        setupData()

        expect:
        billingService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Billing> billingList = billingService.list(max: 2, offset: 2)

        then:
        billingList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        billingService.count() == 5
    }

    void "test delete"() {
        Long billingId = setupData()

        expect:
        billingService.count() == 5

        when:
        billingService.delete(billingId)
        sessionFactory.currentSession.flush()

        then:
        billingService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Billing billing = new Billing()
        billingService.save(billing)

        then:
        billing.id != null
    }
}
