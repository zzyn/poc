package com.webflux.cassandra.demo.integrationtest;

import com.datastax.driver.core.utils.UUIDs;
import com.webflux.cassandra.demo.EndpointTestBase;
import com.webflux.cassandra.demo.MockData;
import com.webflux.cassandra.demo.core.mapper.PlanMapperUtility;
import com.webflux.cassandra.demo.core.utility.PlanUtility;
import com.webflux.cassandra.demo.domain.dto.PlanDto;
import com.webflux.cassandra.demo.domain.model.PlanCollectionRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanQueryModel;
import com.webflux.cassandra.demo.domain.model.PlanRequestModel;
import com.webflux.cassandra.demo.domain.model.PlanUpdateRequestModel;
import com.webflux.cassandra.demo.domain.model.businessenum.PlanTypeEnum;
import com.webflux.cassandra.demo.domain.model.businessenum.ProductIdEnum;
import com.webflux.cassandra.demo.domain.model.businessenum.StateEnum;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.webflux.cassandra.demo.MockData.*;
import static com.webflux.cassandra.demo.MockData.PLAN_BUSINESS_KEY;
import static com.webflux.cassandra.demo.MockData.PRODUCT_ID;
import static com.webflux.cassandra.demo.core.utility.PlanUtility.generateRandomString;
import static com.webflux.cassandra.demo.domain.Constants.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PlanControllerIT extends EndpointTestBase {
    PlanControllerIT() throws Exception {
        super();
    }

    @DisplayName("givenNormal_WhenInsert_thenTrue")
    @Test
    public void givenNormal_WhenInsert_thenTrue() throws Exception {
        assertInsertModel(getPlanRequest());
    }

    @DisplayName("givenNull_WhenInsert_thenTrue")
    @Test
    public void givenNull_WhenInsert_thenTrue() throws Exception {
        assertInsertModel(getNullPlanRequest());
    }

    @DisplayName("givenNullEmpty_WhenInsert_thenTrue")
    @Test
    public void givenNullEmpty_WhenInsert_thenTrue() throws Exception {
        assertInsertModel(getNullAndEmptyPlanRequest());
    }

    @DisplayName("givenExceptionWithErrorCode_whenInsertBatch_thenBadRequest")
    @ParameterizedTest
    @MethodSource("insert_fails_with_constraintViolation_fixture")
    public void givenExceptionWithErrorCode_whenInsertBatch_thenBadRequest(PlanRequestModel request, String defaultMessage) throws Exception {

        PlanCollectionRequestModel requests = getPlanRequestCollection();
        requests.getRequests().add(request);
        super.getDefaultBuilder().build().post().uri(PLAN_INSERT_BATCH_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(requests)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.errors[0].defaultMessage")
                .isEqualTo(defaultMessage);
    }

    @DisplayName("givenExceptionWithErrorCode_whenInsert_thenBadRequest")
    @ParameterizedTest
    @MethodSource("insert_fails_with_constraintViolation_fixture")
    public void givenExceptionWithErrorCode_whenInsert_thenBadRequest(PlanRequestModel request, String defaultMessage) throws Exception {

        super.getDefaultBuilder().build().post().uri(PLAN_ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(request)
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.errors[0].defaultMessage")
                .isEqualTo(defaultMessage);
    }

    @DisplayName("givenNormal_whenInsertBatch_thenTrue")
    @Test
    public void givenNormal_whenInsertBatch_thenTrue() throws Exception {
        assertInsertBatchModel(getPlanRequestCollection());
    }

    //TODO: need further investigate
    @Disabled("Succeed when debug, but fail when run")
    @DisplayName("givenNormal_whenUpdate_thenTrue")
    @Test
    public void givenNormal_whenUpdate_thenTrue() throws Exception {

        PlanRequestModel requestModel = getPlanRequest();
        PlanDto planInsertResult = assertInsertModel(requestModel);
        PlanUpdateRequestModel planUpdate = PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(requestModel);
        planUpdate.setSystemKey(planInsertResult.getSystemKey());
        planUpdate.setLearningUnit(LEARNING_UNIT_UPDATE_AFTER);
        planUpdate.setCreatedBy(CREATED_BY_UPDATE_AFTER);
        planUpdate.setLastUpdatedBy(LAST_UPDATED_BY_UPDATE_AFTER);
        planUpdate.setPlanType(PlanTypeEnum.DIAGNOSTICTEST.toCode());
        planUpdate.setState(PLAN_STATE_UPDATE_AFTER);
        planUpdate.setRoute(ROUTE_UPDATE_AFTER);
        planUpdate.setCreatedTime(getCurrent());
        planUpdate.setLastUpdatedTime(getCurrent());
        planUpdate.setStartTime(getCurrent());
        planUpdate.setEndTime(getCurrent());
        executeAndAssertUpdateEntity(planUpdate, true);
        PlanDto updateResult=PlanMapperUtility.PLAN_DTO_MAPPER.planUpdateRequestModelToPlanDto(planUpdate);
        assertQueryResultModel(updateResult);
    }

    @DisplayName("givenNonExistPrimaryKey_whenUpdate_thenFalse")
    @ParameterizedTest
    @MethodSource("update_fails_with_constraintViolation_fixture")
    public void givenNonExistPrimaryKey_whenUpdate_thenFalse(PlanUpdateRequestModel request) throws Exception {

        PlanRequestModel requestModel = getPlanRequest();
        PlanDto planInsertResult = assertInsertModel(requestModel);
        if (request.getSystemKey() == null)
            request.setSystemKey(planInsertResult.getSystemKey());
        executeAndAssertUpdateEntity(request, false);
        assertQueryResultModel(planInsertResult);
    }

    @Test
    @DisplayName("givenDifferentPartition_whenInsertBatch_thenTrue")
    public void givenDifferentPartition_whenInsertBatch_thenFail() throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        PlanCollectionRequestModel request=getPlanRequestCollection();
        request.getRequests().get(0).setProductId(ProductIdEnum.FRONTRUNNER.toCode());
        client.post().uri(PLAN_INSERT_BATCH_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(request)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT);
    }

    @DisplayName("givenNormal_whenDelete_Partition_thenTrue")
    @Test
    public void givenNormal_whenDelete_Partition_thenTrue() throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        PlanDto firstResult = insertPlanResult.get(0);
        executeAndAssertDeleteResult(getPartitionPath(firstResult), true);
        assertQueryPartitionPathAndSize(firstResult, 0);
    }

    @DisplayName("givenNormal_whenDelete_FirstClusterKey_thenTrue")
    @Test
    public void givenNormal_whenDelete_FirstClusterKey_thenTrue() throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        executeAndAssertDeleteResult(getFirstClusterKeyPathWithPage(insertPlanResult.get(0),null,null), true);
        assertQueryPartitionPathAndSize(insertPlanResult.get(3), TOTAL_IN_COLLECTION-SAME_CLUSTER_KEY_IN_COLLECTION_COUNT);
    }
    @DisplayName("givenNormal_whenDelete_FullPath_thenTrue")
    @Test
    public void givenNormal_whenDelete_FullPath_thenTrue() throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        executeAndAssertDeleteResult(getFullPath(insertPlanResult.get(0)), true);
        assertQueryPartitionPathAndSize(insertPlanResult.get(3), SAME_CLUSTER_KEY_IN_COLLECTION_COUNT);
    }

    @DisplayName("givenNonExists_whenDelete_thenFalse")
    @ParameterizedTest
    @MethodSource("delete_fails_with_constraintViolation_fixture")
    public void givenNonExists_whenDelete_thenFalse(String queryPath) throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        executeAndAssertDeleteResult(queryPath, false);
        assertQueryPartitionPathAndSize(insertPlanResult.get(0), requestCollectionModel.getRequests().size());
    }
    @DisplayName("givenNormal_whenQueryPartition_thenTrue")
    @Test
    public void givenNormal_whenQueryPartition_thenTrue() throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        assertQueryPartitionPathAndSize(insertPlanResult.get(0), requestCollectionModel.getRequests().size());
    }
    @DisplayName("givenNormal_whenQueryPartition_withLimit_thenTrue")
    @Test
    public void givenNormal_whenQueryPartition_withLimit_thenTrue() throws Exception {
        int limit = 3;
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        for (int i = 1; i <= limit; i++) {
            assertQueryPartitionWithPagePathAndSize(insertPlanResult.get(0), i, null, i);
        }
    }

    @DisplayName("givenNormal_whenQueryPartition_withLimitAndPage_thenTrue")
    @Test
    public void givenNormal_whenQueryPartition_withLimitAndPage_thenTrue() throws Exception {
        int limit = 2;
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        int total=requestCollectionModel.getRequests().size();
        for (int i = 1; i <= total/limit; i++) {
            assertQueryPartitionWithPagePathAndSize(insertPlanResult.get(0), limit, i, limit);
        }
    }

    @DisplayName("givenNormal_whenQueryCluster_thenTrue")
    @Test
    public void givenNormal_whenQueryCluster_thenTrue() throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        assertQueryClusterPathAndSize(insertPlanResult.get(0),null,null, SAME_CLUSTER_KEY_IN_COLLECTION_COUNT);
    }
    @DisplayName("givenNormal_whenQueryCluster_withLimt_thenTrue")
    @Test
    public void givenNormal_whenQueryCluster_withLimt_thenTrue() throws Exception {

        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        for (int i = 1; i <= SAME_CLUSTER_KEY_IN_COLLECTION_COUNT; i++) {
            assertQueryClusterPathAndSize(insertPlanResult.get(0),i,null, i);
        }
    }
    @DisplayName("givenNormal_whenQueryCluster_withLimtAndPage_thenTrue")
    @Test
    public void givenNormal_whenQueryCluster_withLimtAndPage_thenTrue() throws Exception {
        int limit = 2;
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        List<PlanDto> insertPlanResult = assertInsertBatchModel(requestCollectionModel);
        int pageSize=SAME_CLUSTER_KEY_IN_COLLECTION_COUNT%limit==0?SAME_CLUSTER_KEY_IN_COLLECTION_COUNT/limit:(SAME_CLUSTER_KEY_IN_COLLECTION_COUNT/limit)+1;
        for (int i = 1; i <= pageSize; i++) {
            assertQueryClusterPathAndSize(insertPlanResult.get(0),limit,i, limit*i<SAME_CLUSTER_KEY_IN_COLLECTION_COUNT?limit:SAME_CLUSTER_KEY_IN_COLLECTION_COUNT%limit);
        }
    }

    @DisplayName("givenErrorPageParameters_whenQuery_thenBadRequest")
    @ParameterizedTest
    @MethodSource("query_fails_with_constraintViolation_fixture")
    public void givenErrorPageParameters_whenQuery_thenBadRequest(String queryPath) throws Exception {
         assertInsertModel(getPlanRequest());
        executeAndAssertQueryBadRequest(queryPath);
    }

    @AfterEach
    public void clean_test_data() throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        List<String> deletedUrlList=new ArrayList<>();
        deletedUrlList.add(getQueryPath(new PlanQueryModel(PRODUCT_ID, PLAN_BUSINESS_KEY, getDefaultBucketId(), MockData.STUDENT_KEY, null)));
        deletedUrlList.add(getQueryPath(new PlanQueryModel(PRODUCT_ID, PLAN_BUSINESS_KEY, getDefaultBucketId(), MockData.STUDENT_KEY_2, null)));

        deletedUrlList.stream().forEach(queryPath ->
            client.delete().uri(queryPath)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
        );
    }

    private String getPartitionPathWithPage(PlanDto request,Integer limit,Integer pageIndex)
    {
        return MockData.getQueryPathWithPage(new PlanQueryModel(request.getProductId(), request.getPlanBusinessKey(), request.getBucketId(), null, null),limit,pageIndex);
    }

    private String getPartitionPath(PlanDto request)
    {
        return MockData.getQueryPath(new PlanQueryModel(request.getProductId(), request.getPlanBusinessKey(), request.getBucketId(), null, null));
    }

    private String getFirstClusterKeyPathWithPage(PlanDto request,Integer limit,Integer pageIndex)
    {
        return MockData.getQueryPathWithPage(new PlanQueryModel(request.getProductId(), request.getPlanBusinessKey(), request.getBucketId(), request.getStudentKey(), null),limit,pageIndex);
    }

    private String getFullPath(PlanDto request)
    {
        return MockData.getQueryPath(new PlanQueryModel(request.getProductId(), request.getPlanBusinessKey(), request.getBucketId(), request.getStudentKey(), request.getSystemKey()));
    }

    private void assertQueryPartitionPathAndSize(PlanDto request,int expectedValue) throws Exception {
        assertQueryPartitionWithPagePathAndSize(request,null,null,expectedValue);
    }

    private void assertQueryClusterPathAndSize(PlanDto request,Integer limit,Integer pageIndex,int expectedValue) throws Exception {
        String partitionQueryPath=getFirstClusterKeyPathWithPage(request,limit,pageIndex);
        executeAndAssertQueryEntity(partitionQueryPath, expectedValue);
    }

    private void assertQueryPartitionWithPagePathAndSize(PlanDto request,Integer limit,Integer pageIndex,int expectedValue) throws Exception {
        String partitionQueryPath=getPartitionPathWithPage(request,limit,pageIndex);
        executeAndAssertQueryEntity(partitionQueryPath, expectedValue);
    }


    static Stream<Arguments> query_fails_with_constraintViolation_fixture() throws Exception {
        PlanRequestModel requestModel = getPlanRequest();
        String zero_page = MockData.getQueryPathWithPage(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), requestModel.getStudentKey(), null),1,0);
        String nagetive_page = MockData.getQueryPathWithPage(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), requestModel.getStudentKey(), null),1,-1);
        String limit_zero = MockData.getQueryPathWithPage(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), requestModel.getStudentKey(), null),0,1);
        String limit_zero_page_zero = MockData.getQueryPathWithPage(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), requestModel.getStudentKey(), null),0,0);
        String limit_nagetive = MockData.getQueryPathWithPage(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), requestModel.getStudentKey(), null),-1,1);
        return Stream.of(
                arguments(zero_page),
                arguments(nagetive_page),
                arguments(limit_zero),
                arguments(limit_zero_page_zero),
                arguments(limit_nagetive)
        );
    }

    static Stream<Arguments> delete_fails_with_constraintViolation_fixture() throws Exception {
        PlanCollectionRequestModel requestCollectionModel = getPlanRequestCollection();
        PlanRequestModel requestModel = requestCollectionModel.getRequests().get(0);
        String error_student_key = MockData.getQueryPath(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), PlanUtility.generateRandomString(STUDENT_KEY_MAX_LENGTH+1), null));
        String error_bucket_id = MockData.getQueryPath(new PlanQueryModel(requestModel.getProductId(), requestModel.getPlanBusinessKey(),
                BUCKET_ID_MAX_LENGTH + 1, requestModel.getStudentKey(), null));
        String error_business_key = MockData.getQueryPath(new PlanQueryModel(requestModel.getProductId(), PlanUtility.generateRandomString(PLAN_BUSINESS_KEY_MAX_LENGTH+1),
                requestModel.getBucketId(), requestModel.getStudentKey(), null));
        String error_product_id = MockData.getQueryPath(new PlanQueryModel(ProductIdEnum.REMEDIATION.toCode() + 1, requestModel.getPlanBusinessKey(),
                requestModel.getBucketId(), requestModel.getStudentKey(), null));
        return Stream.of(
                arguments(error_student_key),
                arguments(error_bucket_id),
                arguments(error_business_key),
                arguments(error_product_id)
        );
    }

    static Stream<Arguments> insert_fails_with_constraintViolation_fixture() throws Exception {
        PlanRequestModel result_With_0_product_id = getPlanRequest();
        result_With_0_product_id.setProductId(0);

        PlanRequestModel result_With_max_value_plus_one_product_id = getPlanRequest();
        result_With_max_value_plus_one_product_id.setProductId(ProductIdEnum.REMEDIATION.toCode() + 1);

        PlanRequestModel result_With_empty_product_business_key = getPlanRequest();
        result_With_empty_product_business_key.setPlanBusinessKey("");
        PlanRequestModel result_With_blank_product_business_key = getPlanRequest();
        result_With_blank_product_business_key.setPlanBusinessKey("     ");
        PlanRequestModel result_With_max_length_plus_one_product_business_key = getPlanRequest();
        result_With_max_length_plus_one_product_business_key.setPlanBusinessKey(generateRandomString(PLAN_BUSINESS_KEY_MAX_LENGTH + 1));
        PlanRequestModel result_With_spefical_characters_product_business_key = getPlanRequest();
        result_With_spefical_characters_product_business_key.setPlanBusinessKey(generateRandomString(PLAN_BUSINESS_KEY_MAX_LENGTH - 10) + SPEFICAL_CHARACTERS);
        PlanRequestModel result_With_nul_product_business_key = getPlanRequest();
        result_With_nul_product_business_key.setPlanBusinessKey(null);

        PlanRequestModel result_With_empty_student_key = getPlanRequest();
        result_With_empty_student_key.setStudentKey("");
        PlanRequestModel result_With_blank_student_key = getPlanRequest();
        result_With_blank_student_key.setStudentKey("      ");
        PlanRequestModel result_With_null_student_key = getPlanRequest();
        result_With_null_student_key.setStudentKey(null);
        PlanRequestModel result_With_max_length_plus_one_student_key = getPlanRequest();
        result_With_max_length_plus_one_student_key.setStudentKey(generateRandomString(STUDENT_KEY_MAX_LENGTH + 1));
        PlanRequestModel result_With_spefical_characters_student_key = getPlanRequest();
        result_With_spefical_characters_student_key.setStudentKey(generateRandomString(STUDENT_KEY_MAX_LENGTH - 10) + SPEFICAL_CHARACTERS);

        PlanRequestModel result_With_error_plan_type = getPlanRequest();
        result_With_error_plan_type.setPlanType(0);
        PlanRequestModel result_With_exception_plan_type = getPlanRequest();
        result_With_exception_plan_type.setPlanType(PlanTypeEnum.MOCKTEST.toCode()+1);

        PlanRequestModel result_With_max_length_plus_one_route = getPlanRequest();
        result_With_max_length_plus_one_route.setRoute(generateRandomString(ROUTE_MAX_LENGTH + 1));

        PlanRequestModel result_With_max_length_plus_one_created_by = getPlanRequest();
        result_With_max_length_plus_one_created_by.setCreatedBy(generateRandomString(CREATED_BY_MAX_LENGTH + 1));

        PlanRequestModel result_With_max_length_plus_one_last_updated_by = getPlanRequest();
        result_With_max_length_plus_one_last_updated_by.setLastUpdatedBy(generateRandomString(LAST_UPDATED_BY_MAX_LENGTH + 1));

        PlanRequestModel result_With_error_state = getPlanRequest();
        result_With_error_state.setState(0);
        PlanRequestModel result_With_exception_state = getPlanRequest();
        result_With_exception_state.setState(StateEnum.CANCELED.toCode()+1);

        PlanRequestModel result_With_max_length_plus_one_learning_unit = getPlanRequest();
        result_With_max_length_plus_one_learning_unit.setLearningUnit(generateRandomString(LEARNING_UNIT_MAX_LENGTH + 1));

        return Stream.of(
                arguments(result_With_0_product_id, PRODUCT_ID_ERROR_CODE),
                arguments(result_With_max_value_plus_one_product_id, PRODUCT_ID_ERROR_CODE),
                arguments(result_With_empty_product_business_key, PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_max_length_plus_one_product_business_key, PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_spefical_characters_product_business_key, PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_blank_product_business_key, PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_nul_product_business_key, PLAN_BUSINESS_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_empty_student_key, STUDENT_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_max_length_plus_one_student_key, STUDENT_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_spefical_characters_student_key, STUDENT_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_null_student_key, STUDENT_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_blank_student_key, STUDENT_KEY_LENGTH_ERROR_CODE),
                arguments(result_With_error_plan_type, PLAN_TYPE_ERROR_CODE),
                arguments(result_With_exception_plan_type, PLAN_TYPE_ERROR_CODE),
                arguments(result_With_max_length_plus_one_route, ROUTE_LENGTH_ERROR_CODE),
                arguments(result_With_max_length_plus_one_created_by, CREATED_BY_LENGTH_ERROR_CODE),
                arguments(result_With_error_state, STATE_ERROR_CODE),
                arguments(result_With_exception_state,STATE_ERROR_CODE),
                arguments(result_With_max_length_plus_one_learning_unit,LEARNING_UNIT_LENGTH_ERROR_CODE),
                arguments(result_With_max_length_plus_one_last_updated_by,LAST_UPDATED_BY_LENGTH_ERROR_CODE)
        );
    }

    static Stream<Arguments> update_fails_with_constraintViolation_fixture() throws Exception {
        PlanUpdateRequestModel plan_update_fail_product_id = PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(getPlanRequest());
        plan_update_fail_product_id.setProductId(ProductIdEnum.REMEDIATION.toCode());

        PlanUpdateRequestModel plan_update_fail_plan_business_key = PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(getPlanRequest());
        plan_update_fail_plan_business_key.setPlanBusinessKey(generateRandomString(PLAN_BUSINESS_KEY_MAX_LENGTH - 10));

        PlanUpdateRequestModel plan_update_fail_bucket_id = PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(getPlanRequest());
        plan_update_fail_bucket_id.setBucketId(BUCKET_ID_ERROR);

        PlanUpdateRequestModel plan_update_fail_student_key = PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(getPlanRequest());
        plan_update_fail_student_key.setStudentKey(generateRandomString(STUDENT_KEY_MAX_LENGTH - 10));

        PlanUpdateRequestModel plan_update_fail_system_key = PlanMapperUtility.PLAN_UPDATE_REQUEST_MAPPER.planRequestToPlanUpdateRequest(getPlanRequest());
        plan_update_fail_system_key.setSystemKey(UUIDs.timeBased());

        return Stream.of(
                arguments(plan_update_fail_product_id),
                arguments(plan_update_fail_plan_business_key),
                arguments(plan_update_fail_bucket_id),
                arguments(plan_update_fail_student_key),
                arguments(plan_update_fail_system_key)
        );
    }


    private PlanDto assertInsertModel(PlanRequestModel requestModel) throws Exception {
        FluxExchangeResult<PlanDto> result=executeClientInsertRequest(PLAN_ROOT_PATH,requestModel);
        PlanDto resultModel=result.getResponseBody().blockFirst();
        assertQueryResultModel(resultModel);
        return resultModel;
    }
    private void assertQueryResultCollectionModel(List<PlanDto> requestCollectionModel) throws Exception {
        if(requestCollectionModel==null) return;
        for(PlanDto requestModel : requestCollectionModel){
            assertQueryResultModel(requestModel);
        }

    }
    private void assertQueryResultModel(PlanDto expect) throws Exception {
        EntityExchangeResult<List<PlanDto>> actual=getQueryEntity(new PlanQueryModel(expect.getProductId(),expect.getPlanBusinessKey(),expect.getBucketId(),expect.getStudentKey(),expect.getSystemKey()));
        assertResultModel(actual,expect);
    }
    private List<PlanDto> assertInsertBatchModel(PlanCollectionRequestModel requestModel) throws Exception {
        FluxExchangeResult<PlanDto> request=executeClientInsertRequest(PLAN_INSERT_BATCH_PATH,requestModel);
        List<PlanDto> result=request.getResponseBody().collectList().block();
        assertQueryResultCollectionModel(result);
        return result;
    }

    private EntityExchangeResult<List<PlanDto>> getQueryEntity(PlanQueryModel queryModel) throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        String queryPath = MockData.getQueryPath(queryModel);
       return client.get().uri(queryPath)
                .accept(MediaType.APPLICATION_JSON_UTF8)
               .exchange()
               .expectStatus()
               .isOk()
               .expectHeader()
               .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
               .expectBodyList(PlanDto.class).returnResult();
    }

    private void executeAndAssertUpdateEntity(PlanUpdateRequestModel updateRequestModel, boolean expectedValue) throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        client.put().uri(PLAN_ROOT_PATH)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(updateRequestModel)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Boolean.class)
                .isEqualTo(expectedValue);
    }
    private void executeAndAssertDeleteResult(String queryPath,boolean expectedValue) throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        client.delete().uri(queryPath)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Boolean.class)
                .isEqualTo(expectedValue);
    }
    private void assertResultModel(EntityExchangeResult<List<PlanDto>> actual, PlanDto expect) {
        List<PlanDto> response = actual.getResponseBody();
        Assertions.assertTrue(response.size() > 0);
        PlanDto actualModel = response.get(0);
        Assertions.assertEquals(expect.getProductId(), actualModel.getProductId());
        Assertions.assertEquals(expect.getPlanBusinessKey(), actualModel.getPlanBusinessKey());
        Assertions.assertEquals(expect.getBucketId(), actualModel.getBucketId());
        Assertions.assertEquals(expect.getStudentKey(), actualModel.getStudentKey());
        Assertions.assertEquals(expect.getPlanType(), actualModel.getPlanType());
        Assertions.assertEquals(expect.getRoute(), actualModel.getRoute());
        Assertions.assertEquals(expect.getState(), actualModel.getState());
        Assertions.assertEquals(expect.getCreatedTime(), actualModel.getCreatedTime());
        Assertions.assertEquals(expect.getCreatedBy(), actualModel.getCreatedBy());
        Assertions.assertEquals(expect.getLastUpdatedTime(), actualModel.getLastUpdatedTime());
        Assertions.assertEquals(expect.getLastUpdatedBy(), actualModel.getLastUpdatedBy());
        Assertions.assertEquals(expect.getStartTime(), actualModel.getStartTime());
        Assertions.assertEquals(expect.getEndTime(), actualModel.getEndTime());
        Assertions.assertEquals(expect.getLearningUnit(), actualModel.getLearningUnit());
    }
    private void executeAndAssertQueryEntity(String queryPath,int expectedValue) throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        client.get().uri(queryPath)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .expectBodyList(PlanDto.class)
                .hasSize(expectedValue).returnResult();
    }
    private void executeAndAssertQueryBadRequest(String queryPath) throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        client.get().uri(queryPath)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
    private<T> FluxExchangeResult<PlanDto> executeClientInsertRequest(String path,T requestModel) throws Exception {
        WebTestClient client = getDefaultBuilder().build();
        return client.post().uri(path)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(requestModel)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .returnResult(PlanDto.class);
    }
}
