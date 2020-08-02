package com.ws.ldy.config.datasource;import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;import org.mybatis.spring.annotation.MapperScan;import org.springframework.context.annotation.Bean;import org.springframework.context.annotation.Configuration;import org.springframework.transaction.annotation.EnableTransactionManagement;//Spring boot方式@EnableTransactionManagement@Configuration@MapperScan("com.baomidou.cloud.service.*.mapper*")public class MybatisPlusConfig {    /**     *  分页     *     * @return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer     * @author ws     * @mail 1720696548@qq.com     * @date 2020/4/23 0023 14:18     */    @Bean    public PaginationInterceptor paginationInterceptor() {        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false        // paginationInterceptor.setOverflow(false);        // 设置最大单页限制数量，默认 500 条，-1 不受限制        // paginationInterceptor.setLimit(500);        // 开启 count 的 join 优化,只针对部分 left join        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));        return paginationInterceptor;    }    /**     *   对枚举参数做处理     *     * @return org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer     * @author ws     * @mail 1720696548@qq.com     * @date 2020/4/23 0023 14:18     *///    @Bean//    public Jackson2ObjectMapperBuilderCustomizer customizer() {//        return builder -> builder.featuresToEnable(WRITE_ENUMS_USING_TO_STRING);//    }    /**     * 乐观锁插件     * @return     */    @Bean    public OptimisticLockerInterceptor optimisticLockerInterceptor() {        return new OptimisticLockerInterceptor();    }}