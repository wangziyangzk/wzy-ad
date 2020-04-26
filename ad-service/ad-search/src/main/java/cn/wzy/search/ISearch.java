package cn.wzy.search;

import cn.wzy.search.vo.SearchRequest;
import cn.wzy.search.vo.SearchResponse;

//广告位检索请求
public interface ISearch {
    public SearchResponse fetchAds(SearchRequest searchRequest);
}
