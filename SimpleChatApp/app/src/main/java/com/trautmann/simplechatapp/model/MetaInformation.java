package com.trautmann.simplechatapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Brandon Trautmann
 */

public class MetaInformation {

    @SerializedName("pagination")
    private Pagination pagination;


    public MetaInformation(Pagination pagination) {
        this.pagination = pagination;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    private class Pagination {

        @SerializedName("current_page")
        private int currentPage;

        @SerializedName("per_page")
        private int perPage;

        @SerializedName("page_count")
        private int pageCount;

        @SerializedName("total_count")
        private int totalCount;

        public Pagination(int currentPage, int perPage, int pageCount, int totalCount) {
            this.currentPage = currentPage;
            this.perPage = perPage;
            this.pageCount = pageCount;
            this.totalCount = totalCount;

        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
    }

}
